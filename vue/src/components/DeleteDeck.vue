<template>
  <div id="main">
    <h2>Delete a Deck</h2>  
    <div class="deckinfo">
      <div class="cards" v-for="deck in allTheDecks" :key="deck.deckId">
        <br />
        <br />
      Deck Name: {{ deck.deckName }}
      <br />
        <br />
      Keyword: {{ deck.deckTag }}
      <br />
        <br />
      Is the deck public: {{ deck.isPublic }}
        <br />
        <br />
       <input
          type="button"
          v-bind:id="deck.deckId"
          v-on:click.prevent="deleteDeck($event)"
          value="Delete Deck?"
        />
        <br />
      </div>
    </div>
  </div>
</template>

<script>
import DeckService from "../services/DeckService";
export default {
  data() {
    return { allTheDecks: [], deletedDeck: {}};
  },
  methods: {
    deleteDeck(event) {
      if (
        confirm(
          "Are you sure you want to delete this deck?  This action cannot be undone."
        )
      ) {
          this.deletedDeck = {
            id: event.target.id,
          };
        DeckService.deleteDeck(this.deletedDeck.id)
          .then(response => {
              if (response.status === 200){
                  window.alert("Deck was deleted!");
                  this.$router.push("/displayDeck");
              }
          })
          .catch (error => {
            if (error.response) {
              this.errorMsg =
                "Error deleting a deck. Response received was '" +
                error.response.statusText +
                "'.";
            } else if (error.request) {
              this.errorMsg =
                "Server could not be reached. Please try again.";
            } else {
              this.errorMsg =
                "Request could not be completed.";
            }  
          });
      }
    },
  },
  created() {
    DeckService
      .list()
      .then((deckData) => {
        this.allTheDecks = deckData.data;
      })
      .catch((err) => {
        console.error(err + " uh oh missing cards!");
      });
  },
};
</script>
<style>
.deckinfo {
    display: flex;
    justify-content: center;
    padding: 20pz;
}
.details {
  margin: 20px;
  width: 20%;
  background-color: #5dacbd;
  
}

</style>