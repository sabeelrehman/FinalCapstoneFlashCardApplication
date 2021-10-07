<template>
  <div id="main">
    <h3>Please select a Card to Delete</h3>
    <div class="cardinfo">
      <div class="cards" v-for="card in allTheCards" :key="card.cardId">
        <br />
        <br />
        Card Name: {{ card.cardName }}
        <br />
        <br />
       Front Info: {{ card.frontInfo }}
       <br />
        <br />
       Back Info: {{ card.backInfo }}
       <br />
        <br />
       Is it public? {{ card.isPublic }}
        <br />
        <br />
        <input
          type="button"
          v-bind:id="card.cardId"
          v-on:click.prevent="deleteCard($event)"
          value="Delete Card?"
        />
        <br />
        <br />
        <!-- <button><router-link :to="{ name: 'delete', params: { id: card.cardId } }"
          >Delete this card!</router-link
        ></button> -->
      </div>
    </div>
  </div>
</template>
<script>
import CardService from "../services/CardService";
export default {
  data() {
    return { allTheCards: [], deletedCard: {} };
  },
  methods: {
    deleteCard(event) {
      if (
        confirm(
          "Are you sure you want to delete this card?  This action cannot be undone."
        )
      ) {
        this.deletedCard = {
          id: event.target.id,
        };
        CardService.deleteCard(this.deletedCard.id)
          .then((response) => {
            if (response.status === 200) {
              window.alert("Card was deleted!");
              this.$router.push("/display");
            }
          })
          .catch((error) => {
            if (error.response) {
              this.errorMsg =
                "Error deleting a card. Response received was '" +
                error.response.statusText +
                "'.";
            } else if (error.request) {
              this.errorMsg = "Server could not be reached. Please try again.";
            } else {
              this.errorMsg = "Request could not be completed.";
            }
          });
      }
    },
  },
  created() {
    CardService.list()
      .then((cardData) => {
        this.allTheCards = cardData.data;
      })
      .catch((err) => {
        console.error(err + " uh oh missing cards!");
      });
  },
};
</script>
<style>
.cardinfo {
  display: flex;
  justify-content: center;
}
.details {
  margin: 20px;
  width: 20%;
  background-color: #5dacbd;
}
</style>