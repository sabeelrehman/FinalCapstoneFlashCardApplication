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

public class CardController {

    @Autowired
    CardDao cardDao;

    @Autowired
    DeckDao deckDao;

    @Autowired
    CardDeckDao cardDeckDao;


    @RequestMapping(path = "/createCard", method = RequestMethod.POST)
    public void createCard(@RequestBody Card card, Principal principal) {

        String username = principal.getName();

        cardDao.createCard(card, username);
    }

    @RequestMapping(path = "/listCards", method = RequestMethod.GET)
    public List<Card> listCards() {
        List<Card> allCards =  cardDao.listCards();
        return allCards;
    }

    @RequestMapping(path = "/listPublicCards", method = RequestMethod.GET)
    public List<Card> listPublicCards() {
        List<Card> publicCards =  cardDao.listPublicCards();
        return publicCards;
    }

    @RequestMapping(path = "/listMyCards", method = RequestMethod.GET)
    public List<Card> listMyCards(Principal principal) {
        String username = principal.getName();
        List<Card> listMyCards =  cardDao.listMyCards(username);
        return listMyCards;
    }

    @RequestMapping(path = "/searchPublicCards/{query}", method = RequestMethod.GET)
    public List<Card> searchPublicCards(@PathVariable String query) {
        List<Card> publicCards =  cardDao.searchPublicCards(query);
        return publicCards;
    }

    @RequestMapping(path = "/searchMyCards/{query}", method = RequestMethod.GET)
    public List<Card> searchMyCards(@PathVariable String query, Principal principal) {
        String username = principal.getName();
        List<Card> listMyCards =  cardDao.searchMyCards(username, query);
        return listMyCards;
    }

    @RequestMapping(path = "/deleteCard/{cardId}", method = RequestMethod.DELETE)
    public void deleteCard(@PathVariable long cardId) {

        cardDao.deleteCard(cardId);
    }

    @RequestMapping(path = "/updateCard", method = RequestMethod.PUT)
    public void updateCard(@RequestBody Card card) {

        System.out.println("server?");
        System.out.println(card.getCardId());


        cardDao.updateCard(card);
    }

    @RequestMapping(path="/card/{cardId}", method=RequestMethod.GET)
    public Card returnCardById(@PathVariable long cardId) {

        Card card = cardDao.getCardById(cardId);

        return card;
    }

    @RequestMapping(path="/listCardsInDeck", method=RequestMethod.GET)
    public List<Card> listCardsInDeck(@RequestBody Deck deck){
        List<Card> cardsInDeck = cardDao.listCardsInDeck(deck);

        return cardsInDeck;
    }

}