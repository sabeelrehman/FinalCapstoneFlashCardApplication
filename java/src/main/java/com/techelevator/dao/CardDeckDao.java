package com.techelevator.dao;

import com.techelevator.model.Card;
import com.techelevator.model.CardDeck;
import com.techelevator.model.Deck;

import java.util.List;

public interface CardDeckDao {

    void addCardToDeck(CardDeck cardDeck);
    void removeCardFromDeck(CardDeck cardDeck);
    void removeAllCardsFromDeck(long removeAllFromDeckId);


}
