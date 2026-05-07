package com.scholarsync.backend.controller;
import com.scholarsync.backend.dto.RegisterUserRequest;
import com.scholarsync.backend.model.*;
import com.scholarsync.backend.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/api/users") @CrossOrigin
public class UserController {
  private final UserRepository userRepository;
  public UserController(UserRepository u){this.userRepository=u;}
  @PostMapping
  public User create(@RequestBody RegisterUserRequest req){ return userRepository.save(new User(req.name(), req.email(), req.password(), Role.PARTICIPANT)); }
}

