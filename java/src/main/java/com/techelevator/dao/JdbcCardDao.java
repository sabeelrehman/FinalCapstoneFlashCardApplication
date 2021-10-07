package com.techelevator.dao;

import com.techelevator.model.Card;
import com.techelevator.model.CardDeck;
import com.techelevator.model.Deck;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component

public class JdbcCardDao implements CardDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcCardDao(JdbcTemplate jdbcTemplate){this.jdbcTemplate = jdbcTemplate;}

    @Override
    public void createCard(Card card, String username) {

       long userId=jdbcTemplate.queryForObject("select user_id from users where username = ?",
                int.class, username);

        String sql = "INSERT INTO card " +
                "(card_creator_id, card_creator, front_info, back_info, card_tag, is_public) " +
                "VALUES (?,?,?,?,?,?)";
        jdbcTemplate.update(sql, userId, username,
                 card.getFrontInfo(), card.getBackInfo(),
                card.getCardTag(), card.getIsPublic());
    }

    @Override
    public List<Card> listCards() {
        List<Card> cards = new ArrayList<>();
        String sql = "SELECT * FROM card";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()){
            Card card = mapRowToCard(results);
            cards.add(card);
        }
        return cards;
    }

    @Override
    public List<Card> listPublicCards() {
        List<Card> cards = new ArrayList<>();
        String sql = "SELECT * FROM card WHERE is_public = true";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()){
            Card card = mapRowToCard(results);
            cards.add(card);
        }
        return cards;
    }

    @Override
    public List<Card> listMyCards(String username) {
        List<Card> cards = new ArrayList<>();
        long userId=jdbcTemplate.queryForObject("select user_id from users where username = ?",
                int.class, username);

        String sql = "SELECT * FROM card WHERE card_creator_id = ?";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        while(results.next()){
            Card card = mapRowToCard(results);
            cards.add(card);
        }
        return cards;
    }



    @Override
    public void deleteCard(long cardId) {
        String sql = "DELETE FROM card WHERE card_id = ?";
        jdbcTemplate.update(sql, cardId);

    }

    @Override
    public void updateCard(Card card) {
        String sql = "UPDATE card " +
        "SET front_info = ?, back_info = ?, card_tag = ?, is_public = ? WHERE card_id=?";
        jdbcTemplate.update(sql, card.getFrontInfo(), card.getBackInfo(),
                card.getCardTag(), card.getIsPublic(), card.getCardId());
    }

    @Override
    public Card getCardById(long id) {
        // TODO Auto-generated method stub
        String sql = "SELECT * FROM card where card_id = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, id);
        Card card = null;

        if (result.next()) {
            long cardId = result.getInt("card_id");
            int cardCreatorId = result.getInt("card_creator_id");
            String cardCreator = result.getString("card_creator");
            String frontInfo = result.getString("front_info");
            String backInfo = result.getString("back_info");
            String cardTag = result.getString("card_tag");
            boolean isPublic = result.getBoolean("is_public");

            card = new Card(cardId, cardCreatorId, cardCreator, frontInfo,
                    backInfo, cardTag, isPublic);
        }

        return card;
    }

    @Override
    public List<Card> listCardsInDeck(Deck deck) {
        List<Card> cards = new ArrayList<>();
        String sql = "SELECT * FROM card JOIN card_deck ON card.card_id = card_deck.card_id WHERE card_deck.deck_id = ?";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, deck.getDeckId());
        while(results.next()){
            Card card = mapRowToCard(results);
            cards.add(card);
        }
        return cards;
    }

    @Override
    public List<Card> searchPublicCards(String query) {
        List<Card> cards = new ArrayList<>();
        String sql = "SELECT * FROM card WHERE is_public = true AND card_tag ILIKE ?";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, query);
        while(results.next()){
            Card card = mapRowToCard(results);
            cards.add(card);
        }
        return cards;
    }

    @Override
    public List<Card> searchMyCards(String username, String query) {
        List<Card> cards = new ArrayList<>();
        long userId=jdbcTemplate.queryForObject("select user_id from users where username = ?",
                int.class, username);

        String sql = "SELECT * FROM card WHERE card_creator_id = ? AND card_tag ILIKE ?";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId, query);
        while(results.next()){
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
