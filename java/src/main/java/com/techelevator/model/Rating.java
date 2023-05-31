package com.techelevator.model;

public class Rating {
    private int ratingId;
    private int matchmakingId;
    private int studentId;
    private int companyId;
    private int rating;

    public Rating() {
    }

    public Rating(int ratingId, int matchmakingId, int studentId, int companyId, int rating) {
        this.ratingId = ratingId;
        this.matchmakingId = matchmakingId;
        this.studentId = studentId;
        this.companyId = companyId;
        this.rating = rating;
    }

    public int getRatingId() {
        return ratingId;
    }

    public void setRatingId(int ratingId) {
        this.ratingId = ratingId;
    }

    public int getMatchmakingId() {
        return matchmakingId;
    }

    public void setMatchmakingId(int matchmakingId) {
        this.matchmakingId = matchmakingId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

}
