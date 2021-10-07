package com.techelevator.dao;

import com.techelevator.model.Card;
import com.techelevator.model.Deck;

import java.util.List;

public interface DeckDao {

    void createDeck (Deck deck, String username);
    List<Deck> listDecks(); //view all decks regardless of privacy
    List<Deck> listPublicDecks(); //view public decks
    List<Deck> listMyDecks(String username); //view personal decks
    void deleteDeck(long deckId);
    void updateDeckInfo(Deck deck);
    Deck getDeckByDeckId(long deckId);

}
