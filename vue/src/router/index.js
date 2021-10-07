import Vue from 'vue'
import Router from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Logout from '../views/Logout.vue'
import Register from '../views/Register.vue'
import store from '../store/index'
import DisplayCard from '../views/DisplayCards.vue'
import AddCard from '../components/AddCard.vue';
import DeleteCard from '../views/DeleteCardDetail.vue'
import UpdateCard from '../views/ModifyCard.vue'
import DisplayDeck from '../views/DisplayDecks.vue'
import AddDeck from '../views/AddDecks.vue'
import DeleteDeck from '../views/DeleteDeckDetails.vue'
import UpdateDeck from '../views/ModifyDeck.vue'

Vue.use(Router)

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/login",
      name: "login",
      component: Login,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/logout",
      name: "logout",
      component: Logout,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/register",
      name: "register",
      component: Register,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/display",
      name: "display",
      component: DisplayCard,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/add",
      name: "add",
      component: AddCard,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/delete",
      name: "delete",
      component: DeleteCard,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/update/:cardId",
      name: "update",
      component: UpdateCard,
      meta: {
        requiresAuth: true
      }
    },
     {
      path: "/displayDeck",
      name: "displayDeck",
      component: DisplayDeck,
      meta: {
        requiresAuth: false
      }
     },
     {
       path: "/addDeck",
       name: "addDeck",
       component: AddDeck,
       meta: {
         requiresAuth: true
       }
     },
     {
       path: "/deleteDeck",
       name: "deleteDeck",
       component: DeleteDeck,
       meta: {
         requiresAuth: true
       }
     },
     {
       path: "/updateDeck/:deckId",
       name: "updateDeck",
       component: UpdateDeck,
       met: {
         requiresAuth: true
       }
     },
     
  ]
})
router.beforeEach((to, from, next) => {
  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    next("/login");
  } else {
    // Else let them go to their next destination
    next();
  }
});

export default router;
