<template>
  <div id="main">
    <div>
  <input type="text" id="deckTagFilter" v-model="filter.deckTag" />
        <i>  Search for a Deck  </i>
        <br />
  </div>
    <div class="displaydeck">
      <div class="decks" v-for="deck in filteredList" :key="deck.deckId">
        Deck Id: {{deck.deckId}}
        <br />
        Deck Name: {{ deck.deckName }}
        <br />
        Deck Keyword: {{ deck.deckTag }}
        <br />
        Is the deck public: {{ deck.isPublic }}
        <!--router link below will change -->
        <br />
        <br />
     <button class="button"><router-link :to="{ name: 'display' }">List All Cards in Deck</router-link></button>
        <br />
        <br />
        <button class="button"><router-link :to="{ name: 'updateDeck', params: { deckId: deck.deckId }}">Click to update your deck</router-link></button>
        <br />
        <br />
      </div>
    </div>
    <button class="button"><router-link v-bind:to="{ name: 'deleteDeck'}"> Delete a Deck </router-link></button> 
        <br />
        <br />
  <button><router-link v-bind:to="{ name : 'addDeck'}">Create a new Deck</router-link></button>
    <br />
     <br />
  </div>
</template>

<script>
import DeckService from "@/services/DeckService";

export default {
  data() {
    return {
      filter: {
        deckTag: "",
      },
      allTheDecks: []};
  },

  created() {
    DeckService
      .list()
      .then((deckData) => {
        this.allTheDecks = deckData.data;
      })
      .catch((err) => {
        console.error(err + " uh oh missing decks!");
      });
  },
    computed: {
    filteredList() {
      if (this.filter.deckTag != "") {
      return this.allTheDecks.filter( 
        (deck) => {
            return deck.deckTag.toLowerCase().includes(this.filter.deckTag.toLowerCase());
        } 
      );
      }
      return this.allTheDecks;
    }, 
  },
};
</script>
<style>
.displaydeck {
    display: flex;
    justify-content: center;
    
}
.decks {
  margin: 20px;
  width: 40%;
  background-color: #5dacbd;
  border-radius: 5%;
  background-image: url('../../notecard.png');
  font-family: candara;
  padding: 20px;
}

</style>