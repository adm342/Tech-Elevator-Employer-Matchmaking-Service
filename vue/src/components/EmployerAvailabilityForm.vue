<template>
<div>
<button v-on:click="showForm = !showForm">Add New Availability</button>

  <form @submit.prevent="handleSubmit" id="addScheduleFields" v-show="showForm">
      <div class="field">
          <label for="dateAvailable">Date Available: </label>
          <input name="dateAvailable" type="date" v-model="schedule.dateAvailable" />
      </div>
      <div class="field">
          <label for="startTime">Start Time: </label>
          <input name="startTime" type="time" v-model="schedule.startTime" />
      </div>
      <div class="field">
          <label for="endTime">End Time: </label>
          <input name="endTime" type="time" v-model="schedule.endTime" />
      </div>
      <button type="submit" class="btn save">Save Schedule</button>
    
</form>
</div>
  
</template>

<script>
import companyService from '../services/CompanyService'

export default {
    name: "add-Availability",

    props: {
        scheduleId: {
            type: Number,
            default: 0
        }
    },

    data() {
        return {
            schedule:{
                dateAvailable: "",
                startTime: "",
                endTime: "",
            },
            showForm: false,
            errorMsg: ""
            };
        },
    
    methods: {
        handleSubmit(){
            const newSchedule = {
                firstName: this.employer.firstName,
                dateAvailable: this.schedule.dateAvailable,
                startTime: this.schedule.startTime,
                endTime: this.schedule.endTime
            };

            if (this.scheduleId === 0) {
                companyService.createEmployerAvailability(newSchedule).then(response => {
                    if (response.status === 201) {
                        this.showForm = false;
                        this.$router.push('/');
                    }
                })
                .catch(error => {
                        if (error) {
                            this.errorMsg = "Error adding employer. Employer could not be created.";
                        }
                    });
                    }
        }
    }
    }
</script>

<style>

</style>