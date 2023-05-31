<template>
  <div>
      <h1>Welcome {{ this.$store.state.employer.firstName }}</h1>
  </div>
</template>

<script>
import companyService from '../services/CompanyService'

export default {
    name: "employee",
    
    data() {
        return {
            
            employer: this.filterEmployer,
            employerRoute: this.$route.params.employerID,
        }
    },
    computed: {
        filterEmployer() {
            const employers = this.$store.state.employers;
            return employers.filter(employer => {
                return employer.employerId == this.employerRoute;
            });
        }
    },


    created(){
        companyService.getEmployerByEmployerId(this.$store.state.company.companyId, this.$route.params.employerID).then( 
            (response) => {this.$store.commit("SET_EMPLOYER", response.data);
        })

}
}
</script>

<style>

</style>