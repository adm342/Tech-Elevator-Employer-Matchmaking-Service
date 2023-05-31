package com.techelevator.model;

public class Interview {

    private int interviewId;
    private int matchmakingId;
    private int mmSessionId;
    private int studentId;
    private int employerId;
    private int slot;
    private String location;

    public Interview() {

    }

    public Interview(int interviewId, int matchmakingId, int mmSessionId, int studentId, int employerId, int slot, String location) {
        this.interviewId = interviewId;
        this.matchmakingId = matchmakingId;
        this.mmSessionId = mmSessionId;
        this.studentId = studentId;
        this.employerId = employerId;
        this.slot = slot;
        this.location = location;
    }

    public int getInterviewId() {
        return interviewId;
    }

    public void setInterviewId(int interviewId) {
        this.interviewId = interviewId;
    }

    public int getMatchmakingId() {
        return matchmakingId;
    }

    public void setMatchmakingId(int matchmakingId) {
        this.matchmakingId = matchmakingId;
    }

    public int getMmSessionId() {
        return mmSessionId;
    }

    public void setMmSessionId(int mmSessionId) {
        this.mmSessionId = mmSessionId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getEmployerId() {
        return employerId;
    }

    public void setEmployerId(int employerId) {
        this.employerId = employerId;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
