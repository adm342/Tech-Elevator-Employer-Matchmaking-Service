<template>
  <div>
      <employer-profile />
      <employer-availability-form />

  </div>
</template>

<script>
import EmployerProfile from '../components/EmployerProfile.vue'
import EmployerAvailabilityForm from '../components/EmployerAvailabilityForm.vue'

export default {
    components: {
        EmployerProfile, EmployerAvailabilityForm
    }

}
</script>

<style>

</style>