package com.example.cicd;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestController
public class HelloController {

  @GetMapping("/api/hello")
  public Map<String, Object> hello(@RequestParam(name = "name", required = false) String name) {
    String who = (name == null || name.trim().isEmpty()) ? "CI/CD" : name.trim();

    Map<String, Object> res = new HashMap<>();
    // res.put("message", "Hello " + who + "!");
    res.put("message", "Salut " + who + "!");
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
