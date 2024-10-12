package ch.hackathon.backend.controllers;

import ch.hackathon.backend.models.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
@Slf4j
public class HelloController {
  @GetMapping("hello")
  private String hello(@RequestAttribute User user) {
    log.info("Hello {}", user.getName());
    return "Hello World";
  }
}
