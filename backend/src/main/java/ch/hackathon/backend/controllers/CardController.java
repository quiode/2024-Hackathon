package ch.hackathon.backend.controllers;

import ch.hackathon.backend.dtos.CreateCardDTO;
import ch.hackathon.backend.models.Card;
import ch.hackathon.backend.models.User;
import ch.hackathon.backend.services.CardService;
import ch.hackathon.backend.services.Vote;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/card")
@RequiredArgsConstructor
public class CardController {
    private final CardService cardService;

    @PostMapping("/create")
    public Card createCard(@RequestAttribute User creator,
                           @RequestBody CreateCardDTO ccdto) {
        //Get and pass on all necessary data to create a new card.
        try{
            return cardService.createCard(creator, ccdto.getText(), ccdto.getLectId(), ccdto.getProfId());
        }
        catch (IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    /*
     * Saves that the given user has now upvoted the given card.
     */
    @PostMapping("/upvote/{cardId}")
    public void upvote(@RequestAttribute User user, @PathVariable Long cardId){
        try {
            cardService.vote(user, cardId, Vote.UP);
        }
        catch (IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }

    /*
     * Saves that the given user has now downvoted the given card.
     */
    @PostMapping("/downvote/{cardId}")
    public void downvote(@RequestAttribute User user, @PathVariable Long cardId){
        try {
            cardService.vote(user, cardId, Vote.DOWN);
        }
        catch (IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
