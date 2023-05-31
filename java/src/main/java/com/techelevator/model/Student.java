package com.techelevator.model;

public class Student {

    private int studentId;
    private int userId;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String linkedinUrl;
    private String resumeUrl;
    private int teProgramId;
    private int pathwayId;

    public Student() {
    }

    public Student(int studentId, int userId, String firstName, String lastName, String emailAddress, String linkedinUrl, String resumeUrl, int teProgramId, int pathwayId) {
        this.userId = userId;
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.linkedinUrl = linkedinUrl;
        this.resumeUrl = resumeUrl;
        this.teProgramId = teProgramId;
        this.pathwayId = pathwayId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
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

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getLinkedinUrl() {
        return linkedinUrl;
    }

    public void setLinkedinUrl(String linkedinUrl) {
        this.linkedinUrl = linkedinUrl;
    }

    public String getResumeUrl() {
        return resumeUrl;
    }

    public void setResumeUrl(String resumeUrl) {
        this.resumeUrl = resumeUrl;
    }

    public int getTeProgramId() {
        return teProgramId;
    }

    public void setTeProgramId(int teProgramId) {
        this.teProgramId = teProgramId;
    }

    public int getPathwayId() {
        return pathwayId;
    }

    public void setPathwayId(int pathwayId) {
        this.pathwayId = pathwayId;
    }
}

