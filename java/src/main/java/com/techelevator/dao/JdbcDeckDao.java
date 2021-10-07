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

public class JdbcDeckDao implements DeckDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcDeckDao(JdbcTemplate jdbcTemplate){this.jdbcTemplate = jdbcTemplate; }

    @Override
    public void createDeck(Deck deck, String username) {

        long userId=jdbcTemplate.queryForObject("select user_id from users where username = ?",
                int.class, username);

        String sql = "INSERT INTO deck " +
                "(deck_name, deck_creator_id, deck_creator_name, is_public, deck_tag) " +
                "VALUES (?,?,?,?,?)";
        jdbcTemplate.update(sql, deck.getDeckName(), userId, username, deck.getIsPublic(), deck.getDeckTag());
    }

    @Override
    public List<Deck> listDecks() {
        List<Deck> decks = new ArrayList<>();
        String sql = "SELECT * FROM deck";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()){
            Deck deck = mapRowToDeck(results);
            decks.add(deck);
        }
        return decks;
    }

    @Override
    public List<Deck> listPublicDecks() {
        List<Deck> decks = new ArrayList<>();
        String sql = "SELECT * FROM deck WHERE is_public = true";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()){
            Deck deck = mapRowToDeck(results);
            decks.add(deck);
        }
        return decks;
    }
    @Override
    public List<Deck> listMyDecks(String username) {
        List<Deck> decks = new ArrayList<>();
        long userId=jdbcTemplate.queryForObject("select user_id from users where username = ?",
                int.class, username);

        String sql = "SELECT * FROM deck WHERE deck_creator_id = ?";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        while(results.next()){
            Deck deck = mapRowToDeck(results);
            decks.add(deck);
        }
        return decks;
    }

    @Override
    public void deleteDeck(long deckId) {

        String removeCardsSql = "DELETE FROM card_deck WHERE deck_id =?";
        jdbcTemplate.update(removeCardsSql, deckId);
        String sql = "DELETE FROM deck WHERE deck_id = ?";
        jdbcTemplate.update(sql, deckId);

    }

    @Override
    public void updateDeckInfo(Deck deck) {
        String sql = "UPDATE deck " +
                "SET deck_name = ? , is_public = ? , deck_tag = ? WHERE deck_id = ?";
        jdbcTemplate.update(sql, deck.getDeckName(), deck.getIsPublic(), deck.getDeckTag(), deck.getDeckId());
    }

    public Deck getDeckByDeckId(long deckId) {
        // TODO Auto-generated method stub
        String sql = "SELECT * FROM deck where deck_id = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, deckId);
        Deck deck = null;

        if (result.next()) {
            long id = result.getInt("deck_id");
            String deckName = result.getString("deck_name");
            int deckCreatorId = result.getInt("deck_creator_id");
            String deckCreatorName = result.getString("deck_creator_name");
            boolean isPublic = result.getBoolean("is_public");
            String deckTag = result.getString("deck_tag");

            deck = new Deck(deckId, deckName, deckCreatorId, deckCreatorName,
                    isPublic, deckTag);
        }

        return deck;
    }

    private Deck mapRowToDeck(SqlRowSet rs){
        Deck deck = new Deck();
        deck.setDeckId(rs.getLong("deck_id"));
        deck.setDeckName(rs.getString("deck_name"));
        deck.setDeckCreatorId(rs.getLong("deck_creator_id"));
        deck.setDeckCreatorName(rs.getString("deck_creator_name"));
        deck.setIsPublic(rs.getBoolean("is_public"));
        deck.setDeckTag(rs.getString("deck_tag"));
        return deck;
    }
}
