import axios from "axios";

export default {

    getAllMatchmakingEvents() {
        return axios.get('/matchmaking')
    },
    getMatchmakingEventById(matchmakingId) {
        return axios.get(`/matchmaking/${matchmakingId}`)
    },
    createMatchmakingEvent(newEvent) {
        return axios.post('/matchmaking', newEvent)
    },
    updateMatchmakingEvent(updatedEvent) {
        return axios.put(`/matchmaking/${updatedEvent.matchmakingId}`, updatedEvent)
    },
    createMatchmakingSession(newSession) {
        return axios.post('/matchmaking/sessions', newSession)
    },
    updateMatchmakingSession(updatedSession) {
        return axios.put(`/matchmaking/sessions/${updatedSession.mmSessionId}`, updatedSession)
    },
    createNewStudentRatings(newStudentRatings) {
        return axios.post('/matchmaking/ratings', newStudentRatings)
    },
   
}