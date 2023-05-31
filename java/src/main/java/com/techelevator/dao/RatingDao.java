package com.techelevator.dao;

import com.techelevator.model.Company;
import com.techelevator.model.Rating;
import com.techelevator.model.Student;

import java.util.List;

public interface RatingDao {

    Rating getRatingByRatingId(int ratingId);

    List<Rating> getRatingsByStudentId(int studentId);

    List<Rating> getRatingsByCompanyId(int companyId);

    List<Rating> getRatingsByMatchmakingId(int matchmakingId);

    List<Rating> createSurveyResponses(Rating[] responses);

    void updateSurveyResponses(Rating[] responses);
}
