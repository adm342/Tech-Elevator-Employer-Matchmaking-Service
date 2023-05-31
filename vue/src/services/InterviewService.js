import axios from "axios";

export default {
    getInterview(interviewId){
        return axios.get(`/interviews/${interviewId}`)
    },

    getInterviewByMatchmakingId(){
        return axios.get("/")
    },

    getInterviewByStudentId(){
        return axios.get("/")
    },

    getInterviewByEmployeeId(){
        return axios.get("/")
    },

    
    getAllStudentInterviews() {
        return axios.get('/matchmaking/myInterviews')
    }

}
