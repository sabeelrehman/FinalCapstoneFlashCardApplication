package com.techelevator.model;

public class Deck {

    private long deckId;
    private String deckName;
    private long deckCreatorId;
    private String deckCreatorName;
    private boolean isPublic;
    private String deckTag;

    public Deck (){ //default constructor just in case

    }

    private boolean display;

    public boolean isDisplay() {
        return display;
    }

    public void setDisplay(boolean display) {
        this.display = display;
    }

    public Deck(long deckId, String deckName, long deckCreatorId,
                String deckCreatorName, Boolean isPublic, String deckTag){

        this.deckId = deckId;
        this.deckName = deckName;
        this.deckCreatorId = deckCreatorId;
        this.deckCreatorName = deckCreatorName;
        this.isPublic = isPublic;
        this.deckTag = deckTag;

    }

    public long getDeckId() {
        return deckId;
    }

    public void setDeckId(long deckId) {
        this.deckId = deckId;
    }

    public String getDeckName() {
        return deckName;
    }

    public void setDeckName(String deckName) {
        this.deckName = deckName;
    }

    public long getDeckCreatorId() {
        return deckCreatorId;
    }

    public void setDeckCreatorId(long deckCreatorId) {
        this.deckCreatorId = deckCreatorId;
    }

    public String getDeckCreatorName() {
        return deckCreatorName;
    }

    public void setDeckCreatorName(String deckCreatorName) {
        this.deckCreatorName = deckCreatorName;
    }

    public boolean getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }

    public String getDeckTag() {
        return deckTag;
    }

    public void setDeckTag(String deckTag) {
        this.deckTag = deckTag;
    }
}
