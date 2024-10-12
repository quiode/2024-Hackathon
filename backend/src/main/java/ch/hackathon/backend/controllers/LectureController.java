package ch.hackathon.backend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/card")
@RequiredArgsConstructor
public class LectureController {
    // private final CardService cardService;

    /* @PostMapping("/create")
    public Card createLecture(@RequestAttribute User creator,
                               @RequestBody CreateCardDTO ccdto) {
        //Get and pass on all necessary data to create a new card.
        return cardService.createCard(creator, ccdto.getText(), ccdto.getLecture(), ccdto.getProfessor());
    } */
}
