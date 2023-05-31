<template>
  <div>
      <h4>Representatives</h4>
      <ul>
          <li> 
         <router-link :to="{ name: 'EmployerProfile',params: {companyID:employer.companyId, employerID: employer.employerId }}"
         v-for="employer in this.$store.state.employers"
         v-bind:key="employer.employerId">
         <li>{{ employer.firstName }} {{ employer.lastName }} </li>
         </router-link>
         </li>

      </ul>
  </div>
</template>

<script>
import companyService from '../services/CompanyService'

export default {
name: 'employer-list',
data(){
    return {
        employers: []
    }

},
beforeMount(){
    companyService.getEmployersByCompanyId(this.$store.state.company.companyId).then( (response) => {
        this.$store.commit("SET_EMPLOYERS", response.data);
})
},

methods: {

}
}

</script>

<style>

</style>