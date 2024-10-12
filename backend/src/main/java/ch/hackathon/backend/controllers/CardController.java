package ch.hackathon.backend.controllers;

import ch.hackathon.backend.dtos.CreateCardDTO;
import ch.hackathon.backend.models.Card;
import ch.hackathon.backend.models.User;
import ch.hackathon.backend.services.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/card")
@RequiredArgsConstructor
public class CardController {
    private final CardService cardService;

    @PostMapping("/create")
    public Card createCard(@RequestAttribute User creator,
                           @RequestBody CreateCardDTO ccdto) {
        //Get and pass on all necessary data to create a new card.
        return cardService.createCard(creator, ccdto.getText(), ccdto.getLecture(), ccdto.getProfessor());
    }
}
