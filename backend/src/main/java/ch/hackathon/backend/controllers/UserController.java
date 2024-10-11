package ch.hackathon.backend.controllers;

import ch.hackathon.backend.models.User;
import ch.hackathon.backend.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
  private final UserService userService;

  @PostMapping("/register")
  public User registerUser(@RequestHeader("X-authentik-mail") String mail,
      @RequestHeader("X-authentik-name") String name) {
    return userService.createUser(name, mail);
  }
}
