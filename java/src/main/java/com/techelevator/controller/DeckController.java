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

public class DeckController {

    @Autowired
    CardDao cardDao;

    @Autowired
    DeckDao deckDao;

    @Autowired
    CardDeckDao cardDeckDao;

    @RequestMapping(path = "/createDeck", method = RequestMethod.POST)
    public void createDeck(@RequestBody Deck deck, Principal principal) {

        String userName = principal.getName();

        deckDao.createDeck(deck, userName);

    }

    @RequestMapping(path = "/listDecks", method = RequestMethod.GET)
    public List<Deck> listDecks() {
        List<Deck> allDecks =  deckDao.listDecks();
        return allDecks;
    }

    @RequestMapping(path = "/listPublicDecks", method = RequestMethod.GET)
    public List<Deck> listPublicDecks() {
        List<Deck> publicDecks =  deckDao.listPublicDecks();
        return publicDecks;
    }

    @RequestMapping(path = "/listMyDecks", method = RequestMethod.GET)
    public List<Deck> listMyDecks(Principal principal) {
        String username = principal.getName();
        List<Deck> listMyDecks =  deckDao.listMyDecks(username);
        return listMyDecks;
    }

    @RequestMapping(path = "/deleteDeck/{deckId}", method = RequestMethod.DELETE)
    public void deleteDeck(@PathVariable long deckId) {

        deckDao.deleteDeck(deckId);
    }

    @RequestMapping(path = "/updateDeck", method = RequestMethod.PUT)
    public void updateDeck(@RequestBody Deck deck) {
        System.out.println("server?");
        System.out.println(deck.getDeckId());

        deckDao.updateDeckInfo(deck);
    }

    @RequestMapping(path="/deck/{id}", method=RequestMethod.GET)
    public Deck returnDeckById(@PathVariable long deckId) {

        Deck deck = deckDao.getDeckByDeckId(deckId);

        return deck;
    }


}
