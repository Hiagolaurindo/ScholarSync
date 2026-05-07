package com.scholarsync.backend.service;
import com.scholarsync.backend.model.*;
import com.scholarsync.backend.repository.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EventServiceTest {
 @Mock EventRepository eventRepository; @Mock RegistrationRepository registrationRepository; @Mock UserRepository userRepository;
 @InjectMocks EventService eventService;
 @Test void deveCriarEventoValido(){ Event e=new Event(); e.setTitle("t"); e.setCapacity(1); when(eventRepository.save(e)).thenReturn(e); assertEquals(e, eventService.create(e)); }
 @Test void naoDeveCriarComCapacidadeInvalida(){ Event e=new Event(); e.setTitle("t"); e.setCapacity(0); assertThrows(IllegalArgumentException.class,()->eventService.create(e)); }
 @Test void naoDevePermitirDuplicada(){ when(eventRepository.findById(1L)).thenReturn(Optional.of(evt())); when(userRepository.findById(2L)).thenReturn(Optional.of(new User())); when(registrationRepository.findByUserIdAndEventId(2L,1L)).thenReturn(Optional.of(new Registration())); assertThrows(IllegalArgumentException.class,()->eventService.register(1L,2L)); }
 @Test void naoDevePermitirLotado(){ when(eventRepository.findById(1L)).thenReturn(Optional.of(evt())); when(userRepository.findById(2L)).thenReturn(Optional.of(new User())); when(registrationRepository.findByUserIdAndEventId(2L,1L)).thenReturn(Optional.empty()); when(registrationRepository.countByEventId(1L)).thenReturn(1L); assertThrows(IllegalStateException.class,()->eventService.register(1L,2L)); }
 private Event evt(){ Event e=new Event(); e.setTitle("x"); e.setCapacity(1); return e; }
}

