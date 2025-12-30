package com.example.cicd;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestController
public class HelloController {

  @GetMapping("/api/hello")
  public Map<String, Object> hello() {
    Map<String, Object> res = new HashMap<>();
    res.put("message", "Hello CI/CD!");
    res.put("timestamp", Instant.now().toString());
    return res;
  }

  @GetMapping("/api/healthz")
  public Map<String, Object> healthz() {
    Map<String, Object> res = new HashMap<>();
    res.put("status", "UP");
    return res;
  }
}
