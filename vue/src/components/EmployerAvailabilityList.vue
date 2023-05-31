<template>
  <div>
      <h4>Times Available</h4>
      <ul>
         <li v-for="schedule in availabilities" v-bind:key="schedule.employerAvailabilityId">{{ availabilities.dateAvailable }}: {{ availabilities.startTime }} - {{ availabilities.endTime }}</li>
         
      </ul>
  </div>
</template>

<script>
import companyService from '../services/CompanyService'

export default {
  name: 'employer-availability',
data(){
    return {
        availabilities: []
    }

},
created(){
    companyService.getAvailabilityByEmployerId(this.$store.state.employers.employerId).then( (response) => {
        this.Availabilities = response.data;
})
}
}
  


</script>

<style>

</style>