package com.techelevator.dao;

import com.techelevator.model.Card;
import com.techelevator.model.CardDeck;
import com.techelevator.model.Deck;

import java.util.List;

public interface CardDao {

    void createCard (Card card, String username);
    List<Card> listCards(); //view all cards regardless of privacy admin function
    List<Card> listPublicCards(); //view all public cards
    List<Card> listMyCards(String username);//view personal cards
    void deleteCard(long cardId);
    void updateCard(Card card);
    Card getCardById(long id);
    List<Card>listCardsInDeck(Deck deck);
    List<Card>searchPublicCards(String query);
    List<Card>searchMyCards(String username, String query);

}
