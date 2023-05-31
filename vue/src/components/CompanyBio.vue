<template>
  <div>
<h2>Welcome {{ this.$store.state.company.companyName }} </h2>
<ul>
    <li>Website: {{ this.$store.state.company.websiteUrl }}</li>
    <li>About: {{ this.$store.state.company.description }}</li>
    <li>Job Posting: {{ this.$store.state.company.jobPostingUrl }}</li>
    <li>Youtube Link: {{ this.$store.state.company.youtubeLinkUrl }}</li>
    <li><employer-list /></li>
</ul>

<company-team-form />



</div>
</template>

<script>
import companyService from '../services/CompanyService'
import CompanyTeamForm from '../components/CompanyTeamForm.vue'
import employerList from '../components/EmployerList.vue'


export default {
    name: 'company',
    components: {
        CompanyTeamForm, employerList
    },
    data() {
        return {
            company: {},
        }
    },
    created(){
        companyService.getCompanyByUserId().then( (response) => {
            this.$store.commit("SET_COMPANY", response.data);
        })
    }
}
</script>

<style>

</style>