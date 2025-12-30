package com.example.cicd;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UiController {

  @GetMapping("/")
  public String index(Model model) {
    model.addAttribute("appName", "spring-cicd-demo");
    return "index";
  }

  @GetMapping("/ui/hello")
  public String helloUi(Model model) {
    model.addAttribute("defaultName", "CI/CD");
    return "hello";
  }
}
