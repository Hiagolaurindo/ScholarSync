package com.scholarsync.backend.controller;
import com.scholarsync.backend.dto.*;
import com.scholarsync.backend.model.*;
import com.scholarsync.backend.repository.UserRepository;
import java.util.Map;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/api") @CrossOrigin
// Simple auth controller (mock-style login without JWT).
public class AuthController {
  private final UserRepository userRepository;
  public AuthController(UserRepository u){this.userRepository=u;}
  @PostMapping("/auth/login")
  public ResponseEntity<?> login(@RequestBody LoginRequest req){
    return userRepository.findByEmail(req.email())
      .filter(u -> u.getPassword().equals(req.password()))
      .<ResponseEntity<?>>map(u -> ResponseEntity.ok(new LoginResponse(u.getId(),u.getName(),u.getEmail(),u.getRole())))
      .orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error","Invalid credentials")));
  }
}

