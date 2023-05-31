package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;

public class Company {

    private int companyId;
    private String companyName;
    private String websiteUrl;
    private String description;
    private String jobPostingUrl;
    private String youtubeLinkUrl;
    private String imageUrl;
    private int numberOfTeams;
    private List<Employer> representatives;

    public Company() {
    }


    public Company(int companyId, String companyName, String websiteUrl, String description, String jobPostingUrl,
                   String youtubeLinkUrl, String imageUrl, int numberOfTeams) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.websiteUrl = websiteUrl;
        this.description = description;
        this.jobPostingUrl = jobPostingUrl;
        this.youtubeLinkUrl = youtubeLinkUrl;
        this.imageUrl = imageUrl;
        this.numberOfTeams = numberOfTeams;
        this.representatives = new ArrayList<>();
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getJobPostingUrl() {
        return jobPostingUrl;
    }

    public void setJobPostingUrl(String jobPostingUrl) {
        this.jobPostingUrl = jobPostingUrl;
    }

    public String getYoutubeLinkUrl() {
        return youtubeLinkUrl;
    }

    public void setYoutubeLinkUrl(String youtubeLinkUrl) {
        this.youtubeLinkUrl = youtubeLinkUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getNumberOfTeams() {
        return numberOfTeams;
    }

    public void setNumberOfTeams(int numberOfTeams) {
        this.numberOfTeams = numberOfTeams;
    }

    public List<Employer> getRepresentatives() {
        return representatives;
    }

    public void setRepresentatives(List<Employer> representatives) {
        this.representatives = representatives;
    }

}
