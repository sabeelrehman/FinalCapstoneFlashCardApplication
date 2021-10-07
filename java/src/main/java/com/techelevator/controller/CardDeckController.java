package com.techelevator.controller;

import com.techelevator.dao.CardDao;
import com.techelevator.dao.CardDeckDao;
import com.techelevator.dao.DeckDao;
import com.techelevator.model.Card;
import com.techelevator.model.CardDeck;
import com.techelevator.model.Deck;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@PreAuthorize("isAuthenticated()")
@CrossOrigin

public class CardDeckController {

    @Autowired
    CardDao cardDao;

    @Autowired
    DeckDao deckDao;

    @Autowired
    CardDeckDao cardDeckDao;

    @RequestMapping(path="/addCardToDeck", method=RequestMethod.PUT)
    public void addCardToDeck(@RequestBody CardDeck cardDeck){
        cardDeckDao.addCardToDeck(cardDeck);
    }

    @RequestMapping(path="/removeCardFromDeck", method=RequestMethod.DELETE)
    public void removeCardFromDeck(@RequestBody CardDeck cardDeck){
        cardDeckDao.removeCardFromDeck(cardDeck);
    }


    @RequestMapping(path = "/removeAllCardsFromDeck/{removeAllFromDeckId}", method = RequestMethod.DELETE)
    public void removeAllCardsFromDeck(@PathVariable long removeAllFromDeckId){
        cardDeckDao.removeAllCardsFromDeck(removeAllFromDeckId);
    }


}
