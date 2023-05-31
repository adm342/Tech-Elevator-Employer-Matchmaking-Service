<template>
  
  <div class="matchmaking-form">
    <div class="status-message-error" v-show="errorMsg !== ''">{{errorMsg}}</div>
  <form>
    <h2 class="form-heading">Choose your 4 favorite companies:</h2>
 <div class="selection">   
<label for="Selection 1">Selection  1: </label>
<select id="Selection 1" v-model="companyRatings[0]"> 
	<option v-for="company in companies" v-bind:key="company.companyId" :value="company">
		{{company.companyName}} </option>
          </select>
          </div>
<div class="selection"> 
<label for="Selection 2">Selection 2: </label>
<select id="Selection 2" v-model="companyRatings[1]">
	<option v-for="company in companies" v-bind:key="company.companyId" :value="company">
		{{company.companyName}} </option>
          </select>
</div>
<div class="selection"> 
<label for="Selection 3">Selection 3: </label>
<select id="Selection 3" v-model="companyRatings[2]">
	<option v-for="company in companies" v-bind:key="company.companyId" :value="company">
		{{company.companyName}} </option>
          </select>
          </div>
<div class="selection"> 
<label for="Selection 4">Selection 4: </label>
<select id="Selection 4" v-model="companyRatings[3]">
	<option v-for="company in companies" v-bind:key="company.companyId" :value="company">
		{{company.companyName}} </option>
          </select>
          </div>
    <button type="submit" v-on:click.prevent="submitForm">Submit</button>  
  </form>
  </div>

  
</template>

<script>

import companyService from "../services/CompanyService"
import matchmakingService from "../services/MatchmakingService"
export default {
  name: 'matchmaking-survey-form',
  data() {
    return {

      companyRatings: [],
      companies: [],
      favoriteCompanies: [],
      errorMsg: ""
    }
  },

  methods: {
    submitForm() {
      for(let i=0; i< 4; i++) {
        this.companyRatings[i]
        const newRanking = {
        studentId: this.$store.state.student.studentId,
        companyId: this.companyRatings[i].companyId,
        // matchmakingId: this.$store.state.matchmakingEvent.matchmakingId,
        matchmakingId: 1,
        rating: i+1
        }
        this.favoriteCompanies.push(newRanking)
      }
      this.$store.commit("SET_FAVORITE_COMPANIES",this.favoriteCompanies)
      matchmakingService.createNewStudentRatings(this.favoriteCompanies).then(response => {
        if (response.status === 201) {
          this.errorMsg = "Ratings successfully submitted. Thank you!"
        }
      })
      .catch(error => {
        if (error) {
        this.errorMsg = "Error adding company rankings."
        }
      })
    }
  },


  created(){
    companyService.getAllCompanies().then( (response) => {
        this.companies = response.data;
    })
}
}
</script>

<style scoped>

h2 {
  display: block;
    font-size: 250%;
    background-color: #DAE8F2;
    color: black;
    text-align: center;
    border-radius: 15px;

}

.matchmaking-form {
  display: block;
justify-content: space-evenly;
font-size: 200%;

}

.matchmaking-form button {
background-color: #FF6359;
color: #DAE8F2;
margin-left: 2%;
font-size: 100%;
border-radius: 10px;
}

form {
  display: flex;
  flex-direction: column;
}

label {
  color: #DAE8F2;
  background-color: #999DA0;
  border-radius: 10px;
  border: 2px solid black;
  margin: 2% 2% 2% 0;
}

.selection {
  margin: 2% 2% 2% 0;
}


</style> 