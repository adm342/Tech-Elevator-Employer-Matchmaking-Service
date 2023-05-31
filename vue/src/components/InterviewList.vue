<template>
<div>
    <h1>Interviews</h1>
    <ul>
        <li v-for="interview in interviews" v-bind:key="interview.id">
        {{interview.id}}>
        <li v-for="interview in interviews" v-bind:key="interview.id">
        {{interview.id}}>
        <li v-for="interview in interviews" v-bind:key="interview.id">
        {{interview.id}}>
        <li v-for="interview in interviews" v-bind:key="interview.id">
        {{interview.id}}>

        </li>
    </ul>       
</div>
</template>

<script>
import interviewService from '../services/InterviewService';  

export default {
    name: 'interview-list',
    data(){
        return {
            interviews: []
        }
    },

    created() {
        interviewService.getAllInterviews(this.$store.state.interview.interviewId).then( (response) => {
            this.$store.commit("SET_INTERVIEW", response.data);
        })
    }


}
</script>

<style scoped>

</style>