<template>
  <div>
    <ul v-for="session in matchmakingEvent.mmSessions" v-bind:key="session.mmSessionId"  >
      <li>Session #{{ session.mmSessionNumber }}</li>
      <li>Date: {{ session.mmSessionDate }}</li>
    </ul>
    <button onclick="window.location.href=`/admin/matchmaking/${this.matchmakingEvent.matchmakingId}/session/create`">Add Matchmaking Session</button>
  </div>
</template>

<script>
import MatchmakingService from "../../services/MatchmakingService"

export default {
    name: "matchmaking-detail",
    props: {
      mmEvent: {
        type: Number,
        default: 0
      }
    },
    data() {
      return {
        matchmakingEventt: {}
      }
    },
    created() {
      MatchmakingService
        .getMatchmakingEventById(this.props.event)
        .then(response => {
            this.matchmakingEvents = response.data;
        })
        .catch(error => {
            if (error) {
                this.errorMsg = "Error getting events. Request could not be fulfilled."
            }
        });
    }
}
</script>

<style>

</style>