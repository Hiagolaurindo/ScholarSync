package com.scholarsync.backend.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "event_id"}))
public class Registration {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false) private User user;
    @ManyToOne(optional = false) private Event event;
    private LocalDate registrationDate;
    public Registration() {}
    public Registration(User user, Event event, LocalDate registrationDate) { this.user=user; this.event=event; this.registrationDate=registrationDate; }
    public Long getId() { return id; }
    public User getUser() { return user; }
    public Event getEvent() { return event; }
    public LocalDate getRegistrationDate() { return registrationDate; }
}

