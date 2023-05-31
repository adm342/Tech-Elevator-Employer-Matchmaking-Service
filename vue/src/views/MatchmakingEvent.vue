<template>
  <div>
    <div class="CountdownAPI">
      <ExternalAPI />
    </div>
    <matchmaking-detail />
  </div>
</template>

<script>
import MatchmakingDetail from "../components/dashboard/MatchmakingDetail.vue";
import ExternalAPI from "../components/ExternalAPI.vue";

export default {
  components: {
    MatchmakingDetail,
    ExternalAPI,
  },
};
</script>

<style>
</style>