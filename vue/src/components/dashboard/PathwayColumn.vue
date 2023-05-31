<template>
  <div>
      <h2>{{ title }}</h2>
      <div class="matchmaking-events">
          <div
          class="matchmaking-event"
          v-for="event in matchmakingEvents"
          v-bind:key="event.matchmakingId"
          v-on:click="viewMatchmakingEventDetails(event.matchmakingId)"
          >
          <h3>Matchmaking</h3>
          <h4>Start time: {{ event.startTime }}</h4>
          <h4># of Sessions: {{ event.numberOfSessions }}</h4>
          </div>
      </div>
  </div>
</template>

<script>
export default {
    name: 'pathway-column',
    props: ['title', 'matchmakingEvents', 'pathwayID'],
    methods: {
        viewMatchmakingEventDetails(matchmakingID) {
            this.$router.push(`/pathway/${this.pathwayID}/matchmaking/${matchmakingID}`);
        }
    }
}
</script>

<style>

</style>