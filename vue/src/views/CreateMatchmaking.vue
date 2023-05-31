<template>
  <div>
      <matchmaking-event-form v-show="$route.path === '/admin/matchmaking/create'" />
      <matchmaking-session-form v-show="$route.path !== '/admin/matchmaking/create'" />
  </div>
</template>

<script>
import MatchmakingEventForm from "../components/MatchmakingEventForm.vue"
import MatchmakingSessionForm from "../components/MatchmakingSessionForm.vue"

export default {
    components: {
        MatchmakingEventForm,
        MatchmakingSessionForm
    }
}
</script>

<style>

</style>