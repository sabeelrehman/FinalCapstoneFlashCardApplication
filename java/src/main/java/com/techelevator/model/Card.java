package com.techelevator.model;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.ArrayList;
import java.util.List;

public class Card {

    private long cardId;
    private long cardCreatorId;
    private String cardCreator;
    private String frontInfo;
    private String backInfo;
    private String cardTag;
    private boolean isPublic;

    public boolean isDisplay() {
        return display;
    }

    public void setDisplay(boolean display) {
        this.display = display;
    }

    private boolean display;

    public Card(long cardId, long cardCreatorId, String cardCreator,
                String frontInfo, String backInfo, String cardTag, boolean isPublic) {
        this.cardId= cardId;
        this.cardCreatorId = cardCreatorId;
        this.cardCreator = cardCreator;
        this.frontInfo = frontInfo;
        this.backInfo = backInfo;
        this.cardTag = cardTag;
        this.isPublic = isPublic;

    }

    public Card(String frontInfo) { //default constructor just in case
        this.frontInfo = frontInfo;
    }
    public Card() { //default constructor just in case

    }

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public Long getCardCreatorId() {
        return cardCreatorId;
    }

    public void setCardCreatorId(Long cardCreatorId) {
        this.cardCreatorId = cardCreatorId;
    }

    public String getCardCreator() {
        return cardCreator;
    }

    public void setCardCreator(String cardCreator) {
        this.cardCreator = cardCreator;
    }

    public String getFrontInfo() {
        return frontInfo;
    }

    public void setFrontInfo(String frontInfo) {
        this.frontInfo = frontInfo;
    }

    public String getBackInfo() {
        return backInfo;
    }

    public void setBackInfo(String backInfo) {
        this.backInfo = backInfo;
    }

    public String getCardTag() {
        return cardTag;
    }

    public void setCardTag(String cardTag) {
        this.cardTag = cardTag;
    }

    public boolean getIsPublic() {
        return isPublic;
    }

    public void setPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }
}



