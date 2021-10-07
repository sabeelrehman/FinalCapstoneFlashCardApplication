package com.techelevator.model;

public class CardDeck {
    private long deckId;
    private long cardId;

    public CardDeck(){

    }
    public CardDeck(long deckId, long cardId){
        this.deckId = deckId;
        this.cardId = cardId;
    }

    public long getDeckId() {
        return deckId;
    }

    public void setDeckId(long deckId) {
        this.deckId = deckId;
    }

    public long getCardId() {
        return cardId;
    }

    public void setCardId(long cardId) {
        this.cardId = cardId;
    }
}
