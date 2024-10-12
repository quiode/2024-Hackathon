package ch.hackathon.backend.controllers;

import ch.hackathon.backend.services.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/game")
@RequiredArgsConstructor
public class GameController {
  private final GameService gameService;



}
