package com.techelevator.model;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;


public class EmployerAvailability {

    private int employerAvailabilityId;
    private int employerId;
    private LocalDate dateAvailable;
    private LocalTime startTime;
    private LocalTime endTime;

    public EmployerAvailability() {

    }

    public int getEmployerAvailabilityId() {
        return employerAvailabilityId;
    }

    public void setEmployerAvailabilityId(int employerAvailabilityId) {
        this.employerAvailabilityId = employerAvailabilityId;
    }

    public int getEmployerId() {
        return employerId;
    }

    public void setEmployerId(int employerId) {
        this.employerId = employerId;
    }

    public LocalDate getDateAvailable() {
        return dateAvailable;
    }

    public void setDateAvailable(LocalDate dateAvailable) {
        this.dateAvailable = dateAvailable;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }
}
