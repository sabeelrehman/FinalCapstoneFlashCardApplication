import axios from 'axios';

export default {
  list() {
    return axios.get('/listDecks'); //should this be more specific?
  },
  
  getDeck(id) {
    return axios.get(`/deck/${id}`);
  },
  addDeck(deck) {
    return axios.post('/createDeck', deck);
  },
  deleteDeck(deckId) {
    return axios.delete(`/deleteDeck/${deckId}`);
  },

  updateDeck(deck){
    return axios.put('/updateDeck', deck);
  },
  
}