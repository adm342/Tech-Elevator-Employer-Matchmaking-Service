<template>
<div class="parent">
  <div class="reviews">
  <carousel/>
  </div>
  <div class="aside">
  <navigation-login></navigation-login>  
  </div>
  <body class="container">
      <div class="whyTechElevator">
        <img src="../images/cirr-badge-vf.jpg" alt="CIRR logo">
        <div class="placement">
        <h1>Not all job placement rates are verified like ours.</h1>
        <h2>Our 88% placement rate is independently verified by CIRR. </h2>
        <h3>All bootcamps talk about student outcomes. Where’s the proof? Our data is audited and verified by the independent Council on Integrity in Results Reporting (CIRR) to help create one of the best coding bootcamps around.</h3>
      </div>
      </div>
  </body>
    
  </div>
  
</template>

<script>
import authService from "../services/AuthService";
import navigationLogin from "../components/NavigationLogin.vue"
import carousel from "../components/Carousel.vue"


export default {
  name: "login",
  components: {
    navigationLogin,
    carousel
  },
  data() {
    return {
      user: {
        username: "",
        password: "",
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
};
</script>

<style scoped>

.parent{
  height: calc(100vh);
  background-image: linear-gradient(to bottom, white, #989c9e);
  margin: 0 0 0 0;

}
  
 


.reviews {
display:flex;
flex-direction: column;
justify-content: center;
align-items: center;
position: absolute;
width: 70%;
margin: 0 0 0 10%;
}

.aside {
  display: flex;
  flex-direction: column;
  top: 0;
  left: 0;
  background-color: #2b4f60;
  

}

.container {
display:flex;
flex-direction: column;
justify-content: center;
align-items: center;
position: relative;
height: 100%;
margin: 0 0 0 0;
}


.whyTechElevator {
  display: flex;
  align-items: center;
  position: absolute;
  justify-content: space-evenly;
  height: calc(50vh);
  width: fit-content;
  background-color: #2b4f60;
  margin: 10% 10% 0 5%;
  font-family: 'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;
  color: #dae8f2;
  filter: drop-shadow(5px 5px grey);
  text-align: center;
  font-size: calc(50%);

  
  
}
img {
  width: fit-content;
  height: fit-content;
  filter: drop-shadow(5px 5px yellow);
 }
 .placement{
   font-size: calc(1vh);
 }
</style>