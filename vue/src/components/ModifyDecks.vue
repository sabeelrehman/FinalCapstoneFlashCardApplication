<template>
  <div>
    <h1>Update your deck here</h1>
    <form v-on:submit.prevent='updateDeck'>
        Deck Name: <input type="text" v-model="currentDeck.deckName" />
        <br />
        Deck Tag: <input type="text" v-model="currentDeck.deckTag" />
        <br />
        Is this Deck Public?:  <select id="deck-is-public" v-model="currentDeck.isPublic">
      <option value="true" selected>True</option>
      <option value="false">False</option>
    </select>
        <br />
        <br />
         <button>Update Deck!</button>
        <br />
        <br />
        </form>
      </div>
</template>

<script>
import DeckService from "../services/DeckService";
export default {
  created() {
    this.deckId = this.$route.params.deckId;
    DeckService.getDeck(this.deckId).then(
      (response) => this.currentDeck = response.data
    )
  },
  data(){
    return {
      currentDeck: {},
      deckId: -1
    };
  },
  methods:  {
    updateDeck() {
            //console.log("DEBUG");
      this.currentDeck.deckId = this.deckId;
           // console.log(this.currentCard);

      DeckService.updateDeck(this.currentDeck).then(() => {
          window.alert('Deck info has been updated!');
          this.$router.push({name: 'displayDeck'});
        }
      ).catch(
        (error) => {
          if (error.response) {
            window.alert("Nice try! You failed >:O!")
          }
        });
  },
},
};
// export default {
//   created() {
//     const deckPassed = this.$route.params.id;
//     DeckService.getDeck(deckPassed).then((deckData) => {
//       this.currentDeck = deckData.data;
//     }
//     )
//   },
//   data(){
//     return {
//       currentDeck: {}
//     }
//   },
//   methods:  {
//     updateDeck() {
//       DeckService.updateDeck(this.currentDeck).then(
//         () => {
//           window.alert('Deck info has been updated!')
//           this.$router.push({name: 'displayDeck'})
//         }
//       ).catch(
//         (error) => {
//           if (error.response) {
//             window.alert("Nice try! You failed >:O!")
//           }
//         }
//         );
//   }
// }
// }
</script>