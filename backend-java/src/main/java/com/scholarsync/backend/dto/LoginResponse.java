package com.scholarsync.backend.dto;
import com.scholarsync.backend.model.Role;
public record LoginResponse(Long id, String name, String email, Role role) {}

