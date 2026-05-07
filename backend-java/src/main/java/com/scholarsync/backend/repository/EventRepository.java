package com.scholarsync.backend.repository;
import com.scholarsync.backend.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
public interface EventRepository extends JpaRepository<Event, Long> {}

