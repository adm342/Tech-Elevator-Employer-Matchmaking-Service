package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;

public class Employer {
    private int employerId;
    private int userId;
    private String firstName;
    private String lastName;
    private int companyId;
    private int companyTeam;
    private String title;
    private String email;
    private String linkedinUrl;
    private boolean isPointPerson;
    private List<EmployerAvailability> availabilities;

    public Employer() {
    }

    public Employer(int employerId, int userId, String firstName, String lastName, int companyId, int companyTeam,
                    String title, String email, String linkedinUrl, boolean isPointPerson) {
        this.employerId = employerId;
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.companyId = companyId;
        this.companyTeam = companyTeam;
        this.title = title;
        this.email = email;
        this.linkedinUrl = linkedinUrl;
        this.isPointPerson = isPointPerson;
        this.availabilities = new ArrayList<>();
    }

    public int getEmployerId() {
        return employerId;
    }

    public void setEmployerId(int employerId) {
        this.employerId = employerId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getCompanyTeam() {
        return companyTeam;
    }

    public void setCompanyTeam(int companyTeam) {
        this.companyTeam = companyTeam;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLinkedinUrl() {
        return linkedinUrl;
    }

    public void setLinkedinUrl(String linkedinUrl) {
        this.linkedinUrl = linkedinUrl;
    }

    public boolean isPointPerson() {
        return isPointPerson;
    }

    public void setPointPerson(boolean pointPerson) {
        isPointPerson = pointPerson;
    }

    public List<EmployerAvailability> getAvailabilities() {
        return availabilities;
    }

    public void setAvailabilities(List<EmployerAvailability> availabilities) {
        this.availabilities = availabilities;
    }
}
