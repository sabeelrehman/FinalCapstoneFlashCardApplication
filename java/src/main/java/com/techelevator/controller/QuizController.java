package com.techelevator.controller;

import com.techelevator.dao.CardDao;
import com.techelevator.dao.CardDeckDao;
import com.techelevator.dao.DeckDao;
import com.techelevator.dao.QuizDao;
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

public class QuizController {
    @Autowired
    QuizDao quizDao;


    @RequestMapping(path="/quiz/startQuiz", method=RequestMethod.GET)
    public Card startQuiz(@RequestBody Deck deck){
       long deckId = deck.getDeckId();
       quizDao.startQuiz(deckId);
       return quizDao.displayQuizCard();
    }

    @RequestMapping(path = "/quiz/nextCard", method = RequestMethod.GET)
    public Card nextQuizCard() {
        quizDao.nextQuizCard();
        return quizDao.displayQuizCard();
    }

    @RequestMapping(path = "/quiz/markKnown", method = RequestMethod.GET)
    public Card markCardAsKnown() {
        quizDao.markCardAsKnown();
        return quizDao.displayQuizCard();
    }




}
