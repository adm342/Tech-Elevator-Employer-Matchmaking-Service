<template>
  <div id="navigation-admin">
      <router-link v-bind:to="{ name: 'home' }">Dashboard</router-link>
      <router-link :to="{ name: 'MatchmakingEvents' }">Matchmaking Events</router-link>
      <!-- <router-link v-bind:to="{ name: 'students' }">Current Students</router-link> -->
      <router-link v-bind:to="{ name: 'companies' }">Hiring Partners</router-link>
      <!-- <router-link v-bind:to="{ name: 'interviews' }">Matchmaking Schedule</router-link> -->
      <router-link v-bind:to="{ name: 'logout' }">Logout</router-link>
    </div>
</template>

<script>
export default {
    name: "navigation-admin"

}
</script>

<style scoped>
:any-link {
    display: block;
    flex-direction: column;
    color:aliceblue;
    text-align: center;
    margin: 20% 0 20% 0;
    text-decoration: none;
    font-family: 'Poppins','Trebuchet MS', Arial;
    font-size: 18px;
    line-height: 2;
    font-weight: 200;
    }

  a:hover {
    /* color: #ff6359; */
    background-color: #4a6674;
    width: 100%;
  }

  .router-link-exact-active {
    color: #00adee;
  }
</style>