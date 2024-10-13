package ch.hackathon.backend.controllers;

import ch.hackathon.backend.models.Game;
import ch.hackathon.backend.models.Lecture;
import ch.hackathon.backend.models.LectureTimeframe;
import ch.hackathon.backend.models.User;
import ch.hackathon.backend.repositories.LectureTimeframeRepository;
import ch.hackathon.backend.services.GameService;
import ch.hackathon.backend.services.LectureService;
import ch.hackathon.backend.services.ParticipantService;
import ch.hackathon.backend.services.UserService;
import lombok.RequiredArgsConstructor;

import java.net.HttpCookie;
import java.time.Instant;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/game")
@RequiredArgsConstructor
public class GameController {
  private final GameService gameService;
  private final UserService userService;
  private final LectureService lectureService;
  private final ParticipantService participantService;

  @PostMapping("/lecture/{id}/current")
  public Optional<Long> createGame(@RequestAttribute User user, @PathVariable Long id) {
    Optional<Lecture> lectureRes = lectureService.getById(id);
    Lecture lecture = lectureRes.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "No lecture associated with id"));

    Optional<Game> currentGame = gameService.getCurrentGameForLecture(lecture); 
    if (currentGame.isPresent())
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "");

    LectureTimeframe currentTF = lectureService.getTimeframeForLecture(lecture, Instant.now())
      .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "No current timeframe for lecture"));

    // FIXME: actually let the user/timeframe decide which professor is currently holding the lecture
    return gameService.createGame(lecture, lecture.getProfessors().stream().findAny().get(), currentTF)
            .map(g -> g.getId());
  }

  @GetMapping("/lecture/{id}/current")
  public Optional<Game> getCurrentGame(@RequestAttribute User user, @PathVariable Long id) {
    Lecture lecture = lectureService.getById(id).orElseThrow(
      () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No lecture with specified id")    
    );
    return gameService.getCurrentGameForLecture(lecture);
    
  }

  @PostMapping("/join/{id}")
  public Game joinGame(@RequestAttribute User user, @PathVariable Long id) {  
    Game game = gameService.getById(id).orElseThrow(
      () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No game with this id exists!")
    );
    return userService.joinGame(user, id);
  }

  @PostMapping("/clickcard/{pos}")
  public void clickCard(@RequestAttribute User user, @PathVariable Integer pos) {
    //DOES NOT DO ANYTHING AT THE MOMENT.
    gameService.clickCard(user, pos);
  }
}
