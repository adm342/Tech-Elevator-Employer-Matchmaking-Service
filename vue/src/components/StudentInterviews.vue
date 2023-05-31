<template>
  <div>
      <ul>
         <li v-for="interview in this.$store.state.studentInterviews" v-bind:key="interview.interviewId"><table>
             <tr>
                 <td>Date: </td>
                 <td>{{interview.date}}</td>
             </tr>
             <tr>
                 <td>Start Time:</td>
                 <td>{{interview.startTime}}</td>
             </tr>
             <tr>
                <td>End Time:</td>
                <td>{{interview.endTime}}</td>
             </tr>
             <tr>
                  <td>Company:  </td>
                <td>{{interview.companyName}}</td>
             </tr>
             <tr>
                 <td>Meeting Location:  </td>
                 <td>{{interview.location}}</td>
             </tr>
             </table>
             </li>
             
      </ul>
  </div>
</template>

<script>

import interviewService from '../services/InterviewService'
export default {
    name: "student-interviews",
    data() {
        return {
         studentInterviews: []
        }
    },

    created(){
        interviewService.getAllStudentInterviews().then((response) => {
            this.$store.commit("SET_STUDENT_INTERVIEWS", response.data)
        })
    }
}
</script>

<style>

</style>