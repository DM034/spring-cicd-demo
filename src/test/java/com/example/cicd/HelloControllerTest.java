package com.example.cicd;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class HelloControllerApiTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void hello_default_name_is_cicd() throws Exception {
    mockMvc.perform(get("/api/hello"))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith("application/json"))
        .andExpect(jsonPath("$.message").value("Hello CI/CD!"))
        .andExpect(jsonPath("$.timestamp").exists());
  }

  @Test
  void hello_with_name_param() throws Exception {
    mockMvc.perform(get("/api/hello").param("name", "DM"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.message").value("Hello DM!"))
        .andExpect(jsonPath("$.timestamp").exists());
  }
}
