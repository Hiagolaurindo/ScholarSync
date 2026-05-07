package com.scholarsync.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ApiIntegrationTest {
  @Autowired MockMvc mockMvc;
  @Autowired ObjectMapper objectMapper;

  @Test void loginValido200() throws Exception { mockMvc.perform(post("/api/auth/login").contentType(MediaType.APPLICATION_JSON).content("{\"email\":\"admin@scholarsync.com\",\"password\":\"123456\"}")).andExpect(status().isOk()); }
  @Test void loginInvalido401() throws Exception { mockMvc.perform(post("/api/auth/login").contentType(MediaType.APPLICATION_JSON).content("{\"email\":\"admin@scholarsync.com\",\"password\":\"x\"}")).andExpect(status().isUnauthorized()); }
  @Test void events200() throws Exception { mockMvc.perform(get("/api/events")).andExpect(status().isOk()); }
  @Test void postEvent201() throws Exception { mockMvc.perform(post("/api/events").contentType(MediaType.APPLICATION_JSON).content("{\"title\":\"Evento\",\"description\":\"Desc\",\"date\":\"2026-06-10\",\"location\":\"A\",\"capacity\":10}")).andExpect(status().isCreated()); }
  @Test void deleteEvent() throws Exception { var res = mockMvc.perform(post("/api/events").contentType(MediaType.APPLICATION_JSON).content("{\"title\":\"Del\",\"description\":\"Desc\",\"date\":\"2026-06-10\",\"location\":\"A\",\"capacity\":10}")).andReturn(); String body = res.getResponse().getContentAsString(); Long id = objectMapper.readTree(body).get("id").asLong(); mockMvc.perform(delete("/api/events/"+id)).andExpect(status().isOk()); }
}

