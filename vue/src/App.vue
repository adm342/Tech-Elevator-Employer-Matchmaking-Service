<template>
  <div id="app-view">
    <div id="nav-bar">
    <navigation v-if="$store.state.token != ''"></navigation>
    </div>
    <div id="view-body">
    <router-view />
    </div>
  </div>

</template>

<script>
import Navigation  from './components/Navigation.vue'

export default {
  components: {
    Navigation
  },
}
</script>

<style scoped>
  
  #view-body {
    margin-left: 200px;
    font-family: 'Poppins','Trebuchet MS', Arial;
    /* background-image: linear-gradient(to bottom, white, #dae8f2);
    background-size: 100vh; */


  }
  
  body {
    margin: 0;
    
  }
</style>

