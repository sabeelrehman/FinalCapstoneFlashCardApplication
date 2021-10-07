package com.techelevator.dao;

import com.techelevator.model.Card;
import com.techelevator.model.CardDeck;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component

public class JdbcCardDeckDao implements CardDeckDao{
    private JdbcTemplate jdbcTemplate;

    public JdbcCardDeckDao(JdbcTemplate jdbcTemplate){this.jdbcTemplate = jdbcTemplate;}

    @Override
    public void addCardToDeck(CardDeck cardDeck) {
        String sql = "INSERT INTO card_deck (card_id, deck_id) VALUES (?,?)";
        jdbcTemplate.update(sql, cardDeck.getCardId(), cardDeck.getDeckId());
    }

    @Override
    public void removeCardFromDeck(CardDeck cardDeck) {
        String sql = "DELETE FROM card_deck WHERE card_id = ? AND deck_id = ?";
        jdbcTemplate.update(sql, cardDeck.getCardId(), cardDeck.getDeckId());
    }

    @Override
    public void removeAllCardsFromDeck(long removeAllFromDeckId) {
        String sql = "DELETE FROM card_deck WHERE deck_id =?";
        jdbcTemplate.update(sql, removeAllFromDeckId);
    }

    // =============================================================================================

    public List<Card> listCardsInDeck(CardDeck cardDeck) {

        List<Card> cards = new ArrayList<>();
        String sql = "SELECT * FROM card JOIN card_deck ON card.card_id = card_deck.card_id WHERE card_deck.deck_id = ?";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, cardDeck.getDeckId());
        while (results.next()) {
            Card card = mapRowToCard(results);
            cards.add(card);
        }
        return cards;
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
