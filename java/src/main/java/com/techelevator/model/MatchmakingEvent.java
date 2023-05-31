package com.techelevator.model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class MatchmakingEvent {

    private int matchmakingId;
    private int pathwayId;
    private int numberOfSessions;
    private LocalTime startTime;
    private int numberOfSlots;
    private int slotDurationInMinutes;
    private int slotBreakDurationInMinutes;
    private int extendedBreakSlotLocation;
    private int extendedBreakDurationInMinutes;
    private String surveyMethod;
    private List<MatchmakingSession> mmSessions;
    private List<Rating> surveyResults;


    public MatchmakingEvent() {
    }

    public MatchmakingEvent(int matchmakingId, int pathwayId, int numberOfSessions, LocalTime startTime, int numberOfSlots, int slotDurationInMinutes,
                       int slotBreakDurationInMinutes, int extendedBreakSlotLocation, int extendedBreakDurationInMinutes, String surveyMethod) {
        this.matchmakingId = matchmakingId;
        this.pathwayId = pathwayId;
        this.numberOfSessions = numberOfSessions;
        this.startTime = startTime;
        this.numberOfSlots = numberOfSlots;
        this.slotDurationInMinutes = slotDurationInMinutes;
        this.slotBreakDurationInMinutes = slotBreakDurationInMinutes;
        this.extendedBreakSlotLocation = extendedBreakSlotLocation;
        this.extendedBreakDurationInMinutes = extendedBreakDurationInMinutes;
        this.surveyMethod = surveyMethod;
        this.mmSessions = new ArrayList<>();
        this.surveyResults = new ArrayList<>();
    }

    public int getMatchmakingId() {
        return matchmakingId;
    }

    public void setMatchmakingId(int matchmakingId) {
        this.matchmakingId = matchmakingId;
    }

    public int getPathwayId() {
        return pathwayId;
    }

    public void setPathwayId(int pathwayId) {
        this.pathwayId = pathwayId;
    }

    public int getNumberOfSessions() {
        return numberOfSessions;
    }

    public void setNumberOfSessions(int numberOfSessions) {
        this.numberOfSessions = numberOfSessions;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public int getNumberOfSlots() {
        return numberOfSlots;
    }

    public void setNumberOfSlots(int numberOfSlots) {
        this.numberOfSlots = numberOfSlots;
    }

    public int getSlotDurationInMinutes() {
        return slotDurationInMinutes;
    }

    public void setSlotDurationInMinutes(int slotDurationInMinutes) {
        this.slotDurationInMinutes = slotDurationInMinutes;
    }

    public int getSlotBreakDurationInMinutes() {
        return slotBreakDurationInMinutes;
    }

    public void setSlotBreakDurationInMinutes(int slotBreakDurationInMinutes) {
        this.slotBreakDurationInMinutes = slotBreakDurationInMinutes;
    }

    public int getExtendedBreakSlotLocation() {
        return extendedBreakSlotLocation;
    }

    public void setExtendedBreakSlotLocation(int extendedBreakSlotLocation) {
        this.extendedBreakSlotLocation = extendedBreakSlotLocation;
    }

    public int getExtendedBreakDurationInMinutes() {
        return extendedBreakDurationInMinutes;
    }

    public void setExtendedBreakDurationInMinutes(int extendedBreakDurationInMinutes) {
        this.extendedBreakDurationInMinutes = extendedBreakDurationInMinutes;
    }

    public String getSurveyMethod() {
        return surveyMethod;
    }

    public void setSurveyMethod(String surveyMethod) {
        this.surveyMethod = surveyMethod;
    }

    public List<MatchmakingSession> getMmSessions() {
        return mmSessions;
    }

    public void setMmSessions(List<MatchmakingSession> mmSessions) {
        this.mmSessions = mmSessions;
    }

    public List<Rating> getSurveyResults() {
        return surveyResults;
    }

    public void setSurveyResults(List<Rating> surveyResults) {
        this.surveyResults = surveyResults;
    }
}


