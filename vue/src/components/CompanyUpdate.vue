<template>
  <form v-on:submit.prevent>
    <div class="field">
      <label for="companyName">Company Name:</label>
      <input name="companyName" type="text" v-model="companyName" />
    </div>
    <div class="field">
      <label for="websiteUrl">Website URL:</label>
      <input name="websiteUrl" type="text" v-model="websiteUrl" />
    </div>
    <div class="field">
      <label for="description">Description:</label>
      <input name="description" type="text" v-model="description" />
    </div>
    <div class="field">
      <label for="jobPostingUrl">Job Postings: </label>
      <input name="jobPostingUrl" type="text" v-model="jobPostingUrl" />
    </div>
    <div class="field">
      <label for="youtubeUrl">YouTube Link: </label>
      <input name="youtubeUrl" type="text" v-model="youtubeUrl" />
    </div>
    <div class="field">
      <label for="imgUrl">Image Link: </label>
      <input name="imgUrl" type="text" v-model="imgUrl" />
    </div>
    <div class="actions">
      <button type="submit" v-on:click="updateCompany()">Update Company Bio</button>
    </div>
  </form>
</template>

<script>
import companyService from '../services/CompanyService'

export default {
    name: "update-company",
    props: "companyId",
    data() {
        return {
            companyName: "",
            websiteUrl: "",
            description: "",
            jobPostingUrl: "",
            youtubeUrl: "",
            imgUrl: ""
        };
    },
    methods: {
        updateCompany() {
            const company = {
                id: this.companyId,
                name: this.companyName,
                website: this.websiteUrl,
                description: this.description,
                jobPostingUrl: this.jobPostingUrl,
                imgUrl: this.imgUrl
            };
            companyService.updateCompany(company.id, company)
            .then(response => {
                if (response.status === 200) {
                    this.$router.push({name: 'Companies', params: {id: company.id}});
                }
            })
        }
    }
}
</script>

<style>

</style>