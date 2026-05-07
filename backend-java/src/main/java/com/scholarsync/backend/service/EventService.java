package com.scholarsync.backend.service;

import com.scholarsync.backend.model.*;
import com.scholarsync.backend.repository.*;
import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
// Core business rules: event validation and registration constraints.
public class EventService {
  private final EventRepository eventRepository;
  private final RegistrationRepository registrationRepository;
  private final UserRepository userRepository;

  public EventService(EventRepository e, RegistrationRepository r, UserRepository u){this.eventRepository=e;this.registrationRepository=r;this.userRepository=u;}

  public Event create(Event event){
    validate(event);
    return eventRepository.save(event);
  }
  public Event update(Long id, Event input){
    validate(input);
    Event ev = eventRepository.findById(id).orElseThrow();
    ev.setTitle(input.getTitle());ev.setDescription(input.getDescription());ev.setDate(input.getDate());ev.setLocation(input.getLocation());ev.setCapacity(input.getCapacity());
    return eventRepository.save(ev);
  }
  public List<Event> list(){ return eventRepository.findAll(); }
  public Event get(Long id){ return eventRepository.findById(id).orElseThrow(); }
  public void delete(Long id){ registrationRepository.deleteByEventId(id); eventRepository.deleteById(id); }
  // Registers a participant only if not duplicate and capacity is available.
  public Registration register(Long eventId, Long userId){
    Event event = get(eventId);
    userRepository.findById(userId).orElseThrow();
    if (registrationRepository.findByUserIdAndEventId(userId,eventId).isPresent()) throw new IllegalArgumentException("Duplicate registration");
    if (registrationRepository.countByEventId(eventId) >= event.getCapacity()) throw new IllegalStateException("Event full");
    return registrationRepository.save(new Registration(userRepository.findById(userId).orElseThrow(), event, LocalDate.now()));
  }
  public List<Registration> byUser(Long userId){ return registrationRepository.findByUserId(userId); }
  private void validate(Event event){
    if (event.getTitle()==null || event.getTitle().isBlank()) throw new IllegalArgumentException("Title required");
    if (event.getCapacity()==null || event.getCapacity()<=0) throw new IllegalArgumentException("Capacity must be > 0");
  }
}

