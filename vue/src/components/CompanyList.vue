<template>
  <div>
      <h1>Companies</h1>
      <ul>
         <li v-for="company in companies" v-bind:key="company.companyId">{{ company.companyName }}</li>
      </ul>
  </div>
</template>

<script>
import companyService from '../services/CompanyService'

export default {
name: 'company-list',
data(){
    return {
        companies: []
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

h1 {
 display: block;
  text-transform: capitalize;
  font-size: 500%;
  font-family: 'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;
  text-align: center;
  background-color: #00ADEE;   
    color: black;
    border-radius: 15px;
    margin-bottom: 10%;
    border: 2px solid black;
    font-weight: 400}


li {
   background-color: #DAE8F2;
   font-size: 300%;
   
   text-align: center;
   margin-left: 50%;
   margin-right: 50%;
   border-radius: 15px;

   border: 2px solid black;   

   margin: 3%;


}


</style>