package com.techelevator.model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MatchmakingSession {

    private int mmSessionId;
    private int matchmakingId;
    private LocalDate mmSessionDate;
    private int mmSessionNumber;
    private List<Interview> schedule;


    public MatchmakingSession() {

    }

    public MatchmakingSession(int mmSessionId, int matchmakingId, LocalDate mmSessionDate, int mmSessionNumber) {
        this.mmSessionId = mmSessionId;
        this.matchmakingId = matchmakingId;
        this.mmSessionDate = mmSessionDate;
        this.mmSessionNumber = mmSessionNumber;
        this.schedule = new ArrayList<>();
    }

    public int getMmSessionId() {
        return mmSessionId;
    }

    public void setMmSessionId(int mmSessionId) {
        this.mmSessionId = mmSessionId;
    }

    public int getMatchmakingId() {
        return matchmakingId;
    }

    public void setMatchmakingId(int matchmakingId) {
        this.matchmakingId = matchmakingId;
    }

    public LocalDate getMmSessionDate() {
        return mmSessionDate;
    }

    public void setMmSessionDate(LocalDate mmSessionDate) {
        this.mmSessionDate = mmSessionDate;
    }

    public int getMmSessionNumber() {
        return mmSessionNumber;
    }

    public void setMmSessionNumber(int mmSessionNumber) {
        this.mmSessionNumber = mmSessionNumber;
    }

    public List<Interview> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<Interview> schedule) {
        this.schedule = schedule;
    }
}
