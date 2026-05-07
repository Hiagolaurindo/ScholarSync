package com.scholarsync.backend.repository;
import com.scholarsync.backend.model.*;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
public interface RegistrationRepository extends JpaRepository<Registration, Long> {
  Optional<Registration> findByUserIdAndEventId(Long userId, Long eventId);
  List<Registration> findByUserId(Long userId);
  long countByEventId(Long eventId);
  void deleteByEventId(Long eventId);
}

