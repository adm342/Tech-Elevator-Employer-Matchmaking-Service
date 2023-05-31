<template>
  <form v-on:submit.prevent="submitMatchmakingEventForm" class="matchmakingEventForm">
      
      <div class="status-message-error" v-show="errorMsg !== ''">{{errorMsg}}</div>

      <label for="pathway-campus">Pathway Campus: </label>
      <select id="pathway-campus" v-model="matchmakingEvent.pathwayId">
          <option value="1">
              National Live Remote</option>
          <option value="2">
              Cincinnati</option>
          <option value="3">
              Cleveland</option>
      </select>

      <label for="number-of-sessions">Number of Matchmaking Sessions: </label>
      <input id="number-of-sessions" type="number" v-model="matchmakingEvent.numberOfSessions" />

      <label for="start-time">Start Time: </label>
      <input id="start-time" type="time" v-model="matchmakingEvent.startTime" />

      <label for="number-of-slots">Number of Interview Slots per Person: </label>
      <input id="number-of-slots" type="number" v-model="matchmakingEvent.numberOfSlots" />

      <label for="slot-duration">Interview Length in Minutes: </label>
      <input id="slot-duration" type="number" v-model="matchmakingEvent.slotDurationInMinutes" />

      <label for="slot-break-duration">Post-interview Break Length in Minutes: </label>
      <input id="slot-break-duration" type="number" v-model="matchmakingEvent.slotBreakDurationInMinutes" />

      <label for="extended-break-slot">Extended Break Slot Position: </label>
      <input id="extended-break-slot" type="number" v-model="matchmakingEvent.extendedBreakSlotLocation" />

      <label for="extended-break-duration">Extended Break Duration in Minutes: </label>
      <input id="extended-break-duration" type="number" v-model="matchmakingEvent.extendedBreakDurationInMinutes" />

      <label for="survey-method">Student Matchmaking Survey Method: </label>
      <select id="survey-method" v-model="matchmakingEvent.surveyMethod">
          <option value="Rank Top 4">
              Rank Top 4 Companies</option>
          <option value="Rank All">
              Rank All Companies</option>
      </select>

      <input type="submit" value="Submit" />

  </form>
</template>

<script>
import MatchmakingService from "../services/MatchmakingService";

export default {
    name: "matchmaking-event-form",
    props: {
        matchmakingID: {
            type: Number,
            default: 0
        }
    },
    data() {
        return {
            matchmakingEvent: {
                pathwayId: 0,
                numberOfSessions: 0,
                startTime: null,
                numberOfSlots: 0,
                slotDurationInMinutes: 0,
                slotBreakDurationInMinutes: 0,
                extendedBreakSlotLocation: 0,
                extendedBreakDurationInMinutes: 0,
                surveyMethod: ""
            },
            errorMsg: ""
        }
    },
    methods: {
        submitMatchmakingEventForm() {
            const newEvent = {
                pathwayId: this.matchmakingEvent.pathwayId,
                numberOfSessions: this.matchmakingEvent.numberOfSessions,
                startTime: this.matchmakingEvent.startTime,
                numberOfSlots: this.matchmakingEvent.numberOfSlots,
                slotDurationInMinutes: this.matchmakingEvent.slotDurationInMinutes,
                slotBreakDurationInMinutes: this.matchmakingEvent.slotBreakDurationInMinutes,
                extendedBreakSlotLocation: this.matchmakingEvent.extendedBreakSlotLocation,
                extendedBreakDurationInMinutes: this.matchmakingEvent.extendedBreakDurationInMinutes,
                surveyMethod: this.matchmakingEvent.surveyMethod
            };

            if (this.matchmakingID === 0) {
                MatchmakingService
                    .createMatchmakingEvent(newEvent)
                    .then(response => {
                        if (response.status === 201) {
                            this.$router.push('/');
                        }
                    })
                    .catch(error => {
                        if (error) {
                            this.errorMsg = "Error adding event. Request could not be created.";
                        }
                    });
            } else {
                newEvent.matchmakingId = this.matchmakingID;
                MatchmakingService
                    .updateMatchmakingEvent(newEvent)
                    .then(response => {
                        if (response === 200) {
                            this.$router.push('/');
                        }
                    })
                    .catch(error => {
                        if (error) {
                            this.errorMsg = "Error updating event. Request could not be updated."
                        }
                    });
            }
        }
    }
}
</script>

<style>

</style>