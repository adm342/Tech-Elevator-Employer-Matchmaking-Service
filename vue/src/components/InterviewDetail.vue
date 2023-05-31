<template>
<div>
    <h1>{{ this.$store.state.interview }}</h1>

    interview_id,
    matchmaking_id,
    student_id,
    employer_id,
    slot,
    location,
    interview start time = matchmaking_start_time + (slot-1) * (slot_length + slot_break_length),
    interview end time = matchmaking_start_time + (slot * slot_length) + ((slot - 1) * slot_break_length)

  
</template>

<script>
import interviewService from '../services/InterviewService';
export default {
    name: 'interview-detail',
    data(){
        return {
            interview: {
                id: null,
                matchmakingId: '',
                company: '',
                student: '',
                employerFirstName: '',
                employerLastName:'',
                employerId: '',
                slot: '',
                location: '',
                interviewStartTime: '',
                interviewEndTime: '',
            }
        };
    },
    created() {
        
    }
}
</script>

<style>

</style>