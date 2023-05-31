<template>
  <div>
    <h1>Pathway Campuses</h1>
    <button onclick="window.location.href='/admin/matchmaking/create'">Create Matchmaking Event</button>
    
    <div class="matchmaking-events">
      <router-link
      :to="{ name: 'MatchmakingEventDetails', params: { pathwayID: matchmakingEvent.pathwayId, matchmakingID: matchmakingEvent.matchmakingId } }"
      v-for="matchmakingEvent in this.matchmakingEvents"
      v-bind:key="matchmakingEvent.matchmakingId"
      :mmEvent="matchmakingEvent.matchmakingId"
      >
      <h3>Matchmaking #{{ matchmakingEvent.matchmakingId }} - {{ getPathwayName(matchmakingEvent.pathwayId) }} Campus</h3>
      <h4>Start time: {{ matchmakingEvent.startTime }}</h4>
      <h4># of Sessions: {{ matchmakingEvent.numberOfSessions }}</h4>
      </router-link>
    </div>
  </div>
</template>

<script>

import MatchmakingService from "../../services/MatchmakingService"

export default {
  name: "matchmaking-list",
  components: {
    
  },
  data() {
    return {
        matchmakingEvents: [{}],
        errorMsg: ""
    };
  },
  methods: {
      getPathwayName(pathwayId) {
        if (pathwayId === 1) {
          return 'National Live Remote'
        } else if (pathwayId === 2) {
          return 'Cincinnati'
        } else {
          return 'Cleveland'
        }
      }
  },
  created() {
      MatchmakingService
        .getAllMatchmakingEvents()
        .then(response => {
            this.matchmakingEvents = response.data;
        })
        .catch(error => {
            if (error) {
                this.errorMsg = "Error getting events. Request could not be fulfilled."
            }
        });
  }
};
</script>

<style>
</style>