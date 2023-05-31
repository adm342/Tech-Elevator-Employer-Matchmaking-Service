<template>
  <form v-on:submit.prevent="submitMatchmakingSessionForm" class="matchmakingSessionForm">
      
    <div class="status-message-error" v-show="errorMsg !== ''">{{errorMsg}}</div>

    <label for="session-date">Session Date: </label>
    <input id="session-date" type="date" v-model="matchmakingSession.mmSessionDate" />

    <label for="session-number">Session #: </label>
    <input id="session-number" type="number" v-model="matchmakingSession.mmSessionNumber" />

    <input type="submit" value="Submit" />

  </form>
</template>

<script>
import MatchmakingService from "../services/MatchmakingService";

export default {
    name: "matchmaking-session-form",
    props: {
        mmSessionID: {
            type: Number,
            default: 0
        }
    },
    data() {
        return {
            matchmakingSession: {
                matchmakingId: 0,
                mmSessionDate: null,
                mmSessionNumber: 0
            },
            errorMsg: ""
        }
    },
    methods: {
        submitMatchmakingSessionForm() {
            const newSession = {
                matchmakingId: Number(this.$route.params.matchmakingID),
                mmSessionDate: this.matchmakingSession.mmSessionDate,
                mmSessionNumber: this.matchmakingSession.mmSessionNumber
            };

            if (this.mmSessionID === 0) {
                MatchmakingService
                    .createMatchmakingSession(newSession)
                    .then(response => {
                        if (response.status === 201) {
                            this.$router.push('/');
                        }
                    })
                    .catch(error => {
                        if (error) {
                            this.errorMsg = "Error adding session. Request could not be created.";
                        }
                    });
            } else {
                newSession.mmSessionId = this.mmSessionID;
                MatchmakingService
                    .updateMatchmakingSession(newSession)
                    .then(response => {
                        if (response === 200) {
                            this.$router.push('/');
                        }
                    })
                    .catch(error => {
                        if (error) {
                            this.errorMsg = "Error updating session. Request could not be updated."
                        }
                    });
            }
        }
    }
}
</script>

<style>

</style>