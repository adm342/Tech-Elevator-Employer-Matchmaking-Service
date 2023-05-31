<template>
<div>
<button v-on:click="showForm = !showForm">Add New Representative</button>

  <form @submit.prevent="handleSubmit" id="addEmployerFields" v-show="showForm">
      <div class="field">
          <label for="firstName">First Name: </label>
          <input name="firstName" type="text" v-model="employer.firstName" />
      </div>
      <div class="field">
          <label for="lastName">Last Name: </label>
          <input name="lastName" type="text" v-model="employer.lastName" />
      </div>
      <div class="field">
          <label for="companyTeam">Company Team: </label>
          <input name="companyTeam" type="text" v-model="employer.companyTeam" />
      </div>
      <div class="field">
          <label for="title">Title: </label>
          <input name="title" type="text" v-model="employer.title" />
      </div>
      <div class="field">
          <label for="email">Email: </label>
          <input name="email" type="text" v-model="employer.email" />
      </div>
      <div class="field">
          <label for="linkedinUrl">Linkedin Url: </label>
          <input name="linkedinUrl" type="text" v-model="employer.linkedinUrl" />
      </div>
      <button type="submit" class="btn save">Save Representative</button>
    
</form>
</div>
</template>

<script>
import companyService from '../services/CompanyService'


export default {
    name: "addEmployer",
    props: {
        employerID: {
            type: Number,
            default: 0
        }
    },

    data() {
        return {
            employer:{
            firstName: "",
            lastName: "",
            companyId: this.$store.state.company.companyId,
            companyTeam: 0,
            title: "",
            email: "",
            linkedinUrl: "",
            isPointPerson: false,
            },
            showForm: false,
            errorMsg: ""
            };

    },
    methods: {
        handleSubmit(){
            const newEmployer = {
                firstName: this.employer.firstName,
                lastName: this.employer.lastName,
                companyId: this.employer.companyId,
                companyTeam: this.employer.companyTeam,
                title: this.employer.title,
                email: this.employer.email,
                linkedinUrl: this.employer.linkedinUrl,
                isPointPerson: this.employer.isPointPerson,
            };

            if (this.employerID === 0) {
                companyService.createEmployer(newEmployer).then(response => {
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
        },
        
    }
    
    
  
        
    


}
</script>

<style>

</style>