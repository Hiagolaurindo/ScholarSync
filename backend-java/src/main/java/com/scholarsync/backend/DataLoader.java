package com.scholarsync.backend;

import com.scholarsync.backend.model.Event;
import com.scholarsync.backend.model.Role;
import com.scholarsync.backend.model.User;
import com.scholarsync.backend.repository.EventRepository;
import com.scholarsync.backend.repository.UserRepository;
import java.time.LocalDate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
// Seeds initial users/events for fast local demo and tests.
public class DataLoader {
  @Bean
  CommandLineRunner seed(UserRepository users, EventRepository events) {
    return args -> {
      users.save(new User("Administrador", "admin@scholarsync.com", "123456", Role.ADMIN));
      users.save(new User("Participante", "user@scholarsync.com", "123456", Role.PARTICIPANT));
      events.save(make("Palestra de Teste de Software"));
      events.save(make("Workshop de Java Spring Boot"));
      events.save(make("Minicurso de React"));
    };
  }

  private Event make(String title) {
    Event e = new Event();
    e.setTitle(title);
    e.setDescription(title);
    e.setDate(LocalDate.of(2026, 6, 10));
    e.setLocation("Auditorio");
    e.setCapacity(50);
    return e;
  }
}

