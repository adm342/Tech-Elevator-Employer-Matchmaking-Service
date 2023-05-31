<template>
<div>
  <h1> Interviews</h1>
  <div>
    <student-interviews v-if="isStudent(user)"></student-interviews>
  </div>
  </div>
</template>

<script>
import studentInterviews from "../components/StudentInterviews.vue"
export default {
 name: "home",
  components: {
    studentInterviews

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
}
</script>

<style>

</style>