<template>
  <div>
      <h3>Add a new FlashCard</h3>
            <form v-on:submit.prevent="addCard">
                Front Info: <input type='text' v-model="newCard.frontInfo" /> <br /><br />
                Back Info: <input type='text' v-model="newCard.backInfo" /> <br /> <br />
                Card Tag: <input type='text' v-model="newCard.cardTag" /> <br /> <br />
                Is the flash card public?: <input type='checkbox' v-model='newCard.isPublic' /> <br>  
                <br>
                <br> 
          <button>Save Your New FlashCard</button>
      </form>
  </div>
</template>

<script>
import CardService from '../services/CardService.js';

export default {
  data () {
    return {
      newCard : {}
    }
  },
  methods: {
    addCard() {
      CardService.addCard(this.newCard).then(
        () => {
          window.alert('Card has been Added!');
          this.newCard = {};
          this.$router.push({name: 'display'});
        }

      );
    }
  }
}
</script>

<style>
#input-section {
  background-color:#5dacbd;
  margin: 20px;
  padding: 20px;
  width: 20%;
}

button {
  font-family: candara;
  font-weight: 400;
  font-size: 20px;
  border-radius: 40%;
  border: 2px solid #a7bcb9;
  background-color: #5dacbd;
  color: azure;
}

div {
  color: #5dacbd;
  font-family: candara;
  font-weight: 400;
  padding: 10px;
}

</style>