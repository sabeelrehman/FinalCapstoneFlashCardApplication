package com.techelevator.dao;

import com.techelevator.model.Card;
import com.techelevator.model.CardDeck;
import com.techelevator.model.Deck;

import java.util.List;

public interface QuizDao {

    void startQuiz(long deckId);
    Card displayQuizCard();
    void nextQuizCard();
    void markCardAsKnown();
}
