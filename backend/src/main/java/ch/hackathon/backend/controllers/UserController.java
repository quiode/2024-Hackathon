package ch.hackathon.backend.controllers;

import ch.hackathon.backend.models.Game;
import ch.hackathon.backend.models.User;
import ch.hackathon.backend.services.GameService;
import ch.hackathon.backend.services.LectureService;
import ch.hackathon.backend.services.UserService;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
  private final UserService userService;
  private final LectureService lectureService;
  private final GameService gameService;


  @PostMapping("/register")
  public User registerUser(@RequestHeader("X-authentik-email") String mail,
      @RequestHeader("X-authentik-name") String name) {
    return userService.createUser(name, mail);
  }

  /**
   * Returns the current user
   */
  @GetMapping("")
  public User getCurrentUser(@RequestAttribute User user) {
    return user;
  }
  /**
   * Returns the current game the user is in
   */
  @GetMapping("/currentGame")
  public Optional<Game> getCurrentGame(@RequestAttribute User user) {
    return gameService.getCurrentGameForUser(user);
  }
}
