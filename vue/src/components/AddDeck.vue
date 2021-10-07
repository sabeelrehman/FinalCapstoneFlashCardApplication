<template>
  <div id="main">
    <h3>Add a New Deck</h3>
    <div id="input-section">
      <form v-on:submit.prevent="addDeck">
        Name: <input id="deck-name" v-model="newDeck.deckName" /> <br /><br />
        Deck Tag: <input id="deck-tag" v-model="newDeck.deckTag" /> <br /><br />
       Is the Deck public?: <input type='checkbox' v-model='newDeck.isPublic' /> <br>
        <!-- <select id="is-public" v-model="deck.isPublic">
          <option value="public" selected>True</option>
          <option value="private">False</option>
        </select> -->
        <br />
        <br />
        <button>Save</button>
      </form>
    </div>
  </div>
</template>

<script>
import DeckService from "../services/DeckService";
export default {
  data () {
    return {
      newDeck : {}
    }
  },
  methods: {
    addDeck() {
      DeckService.addDeck(this.newDeck).then(
        () => {
          window.alert('Deck has been Added!');
          this.newDeck = {};
          this.$router.push({name: 'home'});
        }

      );
    }
  }
}

// export default {
//   data() {
//     return {
//       newDeck: {}
//     }
//   },
//   methods: {
//     addDeck() {
//       console.log("saving deck");
//       deckService
//         .list()
//           const newDeck = {
//             name: this.deck.deckName,
//             deckTag: this.deck.deckTag,
//             isPublic: this.deck.isPublic,
//           };
//           DeckService
//             .addDeck(this.newDeck)
//             .then((response) => {
              
//                 window.alert("deck added!");
//                 this.$router.push("/display");
              
//             })
//             .catch((err) => {
//               console.error(err + " problem adding deck!");
//             });   
//   },
// }
// }
</script>
<style>
#input-section {
  background-color:#e0ebeb;
  color: #24527a;
  margin: 20px;
  padding: 20px;
  width: 20%;
  border-radius: 5%;
  font-family: candara;
}
.button{
  color: #e0ebeb;
  font-size: 20px;
  margin: 10px;
  padding: 5px;
  width: 70%;
  font-family: candara;
  font-weight: 400;
} 

div {
  color: #5dacbd;
  font-family: candara;
  font-weight: 400;
  padding: 10px;
}

</style>