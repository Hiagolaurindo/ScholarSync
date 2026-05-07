package com.scholarsync.backend.controller;
import com.scholarsync.backend.dto.RegistrationRequest;
import com.scholarsync.backend.model.*;
import com.scholarsync.backend.service.EventService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/api") @CrossOrigin
// REST endpoints for events and registrations used by React/Angular.
public class EventController {
  private final EventService eventService;
  public EventController(EventService s){this.eventService=s;}
  @GetMapping("/events") public List<Event> list(){ return eventService.list(); }
  @GetMapping("/events/{id}") public Event get(@PathVariable Long id){ return eventService.get(id); }
  @PostMapping("/events") @ResponseStatus(HttpStatus.CREATED) public Event create(@RequestBody Event e){ return eventService.create(e); }
  @PutMapping("/events/{id}") public Event update(@PathVariable Long id,@RequestBody Event e){ return eventService.update(id,e); }
  @DeleteMapping("/events/{id}") public void delete(@PathVariable Long id){ eventService.delete(id); }
  @PostMapping("/events/{eventId}/registrations") public Registration register(@PathVariable Long eventId,@RequestBody RegistrationRequest req){ return eventService.register(eventId, req.userId()); }
  @GetMapping("/users/{userId}/registrations") public List<Registration> byUser(@PathVariable Long userId){ return eventService.byUser(userId); }
}

