<template>
<body>
  <div class="home"> 
    <h1>Home</h1>
    <h3>Hello! You have successfully logged in as an authenticated user.</h3>
    <h3>Please select from following options: </h3>
    <button><router-link v-bind:to="{ name: 'displayDeck'}">View List of Decks</router-link></button>
  <br />
    <br />
    <br />
    <button><router-link v-bind:to="{ name: 'display' }">View List of Flashcards</router-link></button>
  <br />
   <br />
   <br />
  <button><router-link v-bind:to="{ name : 'addDeck'}">Create a new Deck</router-link></button>
    <br />
     <br />
     <br />
     <button><router-link v-bind:to="{ name : 'add'}">Create a new Flashcard</router-link></button>
        <br />
        <br />
        <br />
<button><router-link v-bind:to="{ name: 'deleteDeck'}">Select Deck to Delete</router-link></button> 
<br />
<br />
        <br />
<button><router-link v-bind:to="{ name: 'delete'}">Select Flashcard to Delete</router-link></button>
<br />
<br />
    </div>
</body>

</template>
<style>
body {
  margin: 0;
  padding: 10px;
  min-height: 200vh;
  text-align: center;
  font-family: candara;

}

h1 {
  color: #a7bcb9;
  margin-top: 20px;
  padding-top: 20px;
  font-family: candara;
  font-weight: 400;
  
}

h2 { 
  color:#a7bcb9;
  font-family: candara;
  font-weight: 400;
}

h3 { 
  color:#a7bcb9;
  font-family: candara;
  font-weight: 400;
}

button{
  color: #24527a;
  font-size: 32px;
  background-color: cornflowerblue;
  margin: 10px;
  padding: 15px;
  width: 50%;
  font-family: candara;
  font-weight: 400;
} 


</style>
 