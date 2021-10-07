package com.techelevator.dao;

import com.techelevator.model.Card;
import com.techelevator.model.CardDeck;
import com.techelevator.model.Deck;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component

public class JdbcQuizDao implements QuizDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcQuizDao(JdbcTemplate jdbcTemplate){this.jdbcTemplate = jdbcTemplate;}

    List<Card> quizDeck;
    int i;


    @Override
    public void startQuiz(long deckId) {//     /deck/startQuiz
        quizDeck = new ArrayList<>();

        i=0;
        String sql = "SELECT * FROM card JOIN card_deck ON card.card_id = card_deck.card_id WHERE card_deck.deck_id = ?";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, deckId);
        while(results.next()){
            Card card = mapRowToCard(results);
            quizDeck.add(card);
        }
        System.out.println("Cards in Deck:"+quizDeck.size());
        displayQuizCard();
    }

    @Override
    public Card displayQuizCard() { //        you need to catch this
        System.out.println("Display"+i);
        System.out.println("CardNumber="+quizDeck.get(i).getCardId());
            return quizDeck.get(i);

    }

    @Override
    public void nextQuizCard() {//     /quiz/nextCard


        if(i+1>=quizDeck.size()){
            i=0;

        }else{

            i++;
        }
        displayQuizCard();

    }



    @Override
    public void markCardAsKnown(){//               /deck/quiz/markKnown

        quizDeck.remove(i);
        i--;
        nextQuizCard();

    }

    private Card mapRowToCard(SqlRowSet rs){
        Card card = new Card();
        card.setCardId(rs.getLong("card_id"));
        card.setCardCreatorId(rs.getLong("card_creator_id"));
        card.setCardCreator(rs.getString("card_creator"));
        card.setFrontInfo(rs.getString("front_info"));
        card.setBackInfo(rs.getString("back_info"));
        card.setCardTag(rs.getString("card_tag"));
        card.setPublic(rs.getBoolean("is_public"));
        return card;
    }
}
