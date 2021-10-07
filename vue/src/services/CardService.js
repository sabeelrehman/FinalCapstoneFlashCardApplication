import axios from 'axios';

export default {
  list() {
    return axios.get('/listCards'); //should this be more specific?
  },
  
  getCard(id) {
    return axios.get(`/card/${id}`);
  },

  addCard(card){
    return axios.post('/createCard', card);
  },

  deleteCard(cardId){
    return axios.delete(`/deleteCard/${cardId}`);
  },

  updateCard(card){
    return axios.put('/updateCard', card);
  },


}