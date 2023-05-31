<template>
<div>
  
  <div class="home">
    <div id="page-body">
      <h1>Home</h1>
    <student-bio v-if="isStudent(user)"/>
    <company-bio v-if="isCompany(user)"/>
    <admin-dashboard v-if="isAdmin(user)"/>
    </div>
  </div>
  </div>
 
</template>

<script>
import CompanyBio from '../components/CompanyBio.vue';
import StudentBio from "../components/StudentBio.vue";
import AdminDashboard from "../components/AdminDashboard.vue"

export default {
  name: "home",
  components: {
    StudentBio,
    CompanyBio,
    AdminDashboard,

  },
  computed: {
    user () {
      return this.$store.state.user;
    }
  },
  methods: {
    isAdmin(user) {
      return user.authorities.some(authority => authority.name === 'ROLE_ADMIN');
    },
    isStudent(user) {
      return user.authorities.some(authority => authority.name === 'ROLE_STUDENT');
    },
    isCompany(user) {
      return user.authorities.some(authority => authority.name === 'ROLE_EMPLOYER');
    }
  }
};
</script>

<style scoped>
</style>
