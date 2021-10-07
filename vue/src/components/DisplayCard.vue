
<template>
  <div id="main">
    <div>
    <input type="text" id="cardTagFilter" v-model="filter.cardTag" />
        <i>   Search for Flashcard   </i>
        <br />
        </div>
    <div class="displaycard">
      <div class="cards" v-for="card in filteredList" :key="card.cardId">
        <br />
        Question: {{ card.frontInfo }}
        <br />
        <p v-if='card.display'>Answer: {{ card.backInfo }}</p>
        <br />
        Keyword: {{ card.cardTag }}
        <br />
        <br />
        Is the card Public? {{ card.isPublic }} 
        <br />
        <!--router link below will change -->
        <br />
       <button><router-link v-bind:to="{ name: 'update', params: {cardId: card.cardId, currentCard : card}}">Update Card</router-link></button>
        <br />
        <br />
        <button v-bind:id='card.cardId' v-on:click='flipCard($event)'>Click to see answer</button>
      </div>
    </div>
      <button><router-link v-bind:to="{ name: 'delete'}">Delete Card</router-link></button>
        <br />
        <br />
         <br />
     <button><router-link v-bind:to="{ name : 'add'}">Create a new Flashcard</router-link></button>
        <br />
        <br />
  </div>
</template>

<script>
import CardService from "../services/CardService";
export default {
  data() {
    return {
      filter:{
        cardTag: "",
        cardName: "",
      },
      allTheCards: []
    };
  },
  methods : {
      flipCard(evt) {
        console.log(evt)
        let id = evt.target.id;

        for (let i=0;  i < this.allTheCards.length; i++) {
          

          if (this.allTheCards[i].cardId == id) {

            this.allTheCards[i].display = ! this.allTheCards[i].display;

          }
        }
      }
  },
  created() {
    CardService
      .list()
      .then((cardData) => {
        this.allTheCards = cardData.data;

        // add an attribute to each card called display and set it to false;
      })
      .catch((err) => {
        console.error(err + " uh oh missing cards!");
      });
  },
  computed: {
    filteredList() {
      if (this.filter.cardTag != "") {
      return this.allTheCards.filter( 
        (card) => {
            return card.cardTag.toLowerCase().includes(this.filter.cardTag.toLowerCase());
        } 
      );
      } if (this.filter.cardName != "") {
      return this.allTheCards.filter( 
        (card) => {
            return card.cardName.toLowerCase().includes(this.filter.cardTag.toLowerCase());
    }
      );
  }
   return this.allTheCards;
         },
         
       },     
};
</script>
<style>
.displaycard {
    display: flex;
    justify-content: center;
}
.cards {
  margin: 10px;
  width: 30%;
  background-color: #5dacbd;
  font-family: candara;
  font-weight: 400;
  padding: 15px;
  background-image: url('../../notecard.png');
  border-radius: 5%;
}

</style>