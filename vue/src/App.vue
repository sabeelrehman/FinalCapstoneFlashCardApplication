<template>
  <div id="app">
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
      

      <!--routerlink links to the display cards vue-->
      <!--create a new vue in the views folder for display cards-->
      <!-- the view in the views folder will be displaying the component-->
      <!-- once you create the new view have the controller to tell the program wehre to go enter the URL path-->
      <!-- create path in the router index -->
      <!-- then create and display component which will have the content for the page-->
      <!-- import the component and import view inside the router-->
    </div>
    <router-view />
  </div>
</template>
<style>
#nav {
  background: url(https://wallpaper-house.com/data/out/6/wallpaper2you_113643.jpg) right / cover no-repeat;
}
body {
  font-size: 24px;
  background: url(https://c.wallhere.com/photos/7e/94/2560x1440_px_black_Dark_floor_textures_wood-804857.jpg!d) left / cover no-repeat;
}
a {
  color: azure;
  font-family: candara;
}
</style>