<template>
<div class="navContainer">
  <img src="../images/BetterLogo.png" alt="">
  <div id="login">
    
    <form @submit.prevent="login">
        <h1>Please Sign In</h1>
        <div role="alert" v-if="invalidCredentials">
          Invalid username and password!
        </div>
        <div role="alert" v-if="this.$route.query.registration">
          Thank you for registering, please sign in.
        </div>
        <div class="form-input-group">
          <label for="username">Username:</label>
          <input
            type="text"
            id="username"
            v-model="user.username"
            required
            autofocus
          />
        </div>
        <div class="form-input-group">
          <label for="password">Password: </label>
          <input
            type="password"
            id="password"
            v-model="user.password"
            required
          />
        </div>
        <button type="submit">Sign in</button>
      </form>
  </div>
  
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "navigation-login",
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    }
  }

}
</script>

<style scoped>
.navContainer {
  display: flex;
  flex-direction: column;
  height: calc(100%);
  position: fixed;
  top: 0;
  left: 0;
  width: 200px;
  padding: 10px;
  background-color: #2b4f60;
  align-items: center;
  justify-content: start;
  color: aliceblue;
}

img {
  display: flex;
  flex-direction: column;

  height: 175px;
}
button:hover {
  background-color: #ff6359;
  height: 50px;
  width: 100px;
  filter: drop-shadow(5px 5px grey);
}

</style>