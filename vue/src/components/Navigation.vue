<template>
  <div class="navContainer">
    <img
      src="../images/BetterLogo.png"
    />
    <nav>
      <navigation-admin v-if="isAdmin()"/>
      <navigation-employer v-else-if="isCompany()"/>
      <navigation-student v-else-if="isStudent()"/>
    </nav>
  </div>
</template>

<script>
import NavigationAdmin from './NavigationAdmin.vue';
import NavigationStudent from './NavigationStudent.vue';
import NavigationEmployer from './NavigationEmployer.vue';

export default {
  components: { NavigationAdmin, NavigationStudent, NavigationEmployer },
  name: "navigation",
  computed: {
    user () {
      return this.$store.state.user;
    }
  },
  methods: {
  isAdmin() {
      return this.$store.state.user.authorities.some(authority => authority.name === "ROLE_ADMIN");
    },
  isStudent() {
      return this.$store.state.user.authorities.some(authority => authority.name === "ROLE_STUDENT");
    },
  isCompany() {
     return this.$store.state.user.authorities.some(authority => authority.name === "ROLE_EMPLOYER");
    }
  }
  };

</script>

<style>
.navContainer {
  display: flex;
  flex-direction: column;
  height: 100vh;
  position: fixed;
  top: 0;
  left: 0;
  width: 200px;
  padding: 10px 0 10px 0;
  background-color: #2b4f60;
  align-items: center;
  justify-content: start;
  color: aliceblue;
  
}

img {
  display: flex;
  flex-direction: column;

  height: 150px;
}

/* :any-link:not(#no-any-link, .noAnyLink) {
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
    color: #ff6359;
    background-color: #4a6674;
    width: 100%;
  }

  .router-link-exact-active {
    color: #00adee;
  }
 */

</style>