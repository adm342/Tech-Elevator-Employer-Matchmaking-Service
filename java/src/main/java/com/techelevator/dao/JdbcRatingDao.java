package com.techelevator.dao;

import com.techelevator.model.Company;
import com.techelevator.model.EmployerAvailability;
import com.techelevator.model.Rating;
import com.techelevator.model.Student;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcRatingDao implements RatingDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcRatingDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Rating getRatingByRatingId(int ratingId) {
        Rating rating = null;
        String sql = "SELECT rating_id, matchmaking_id, student_id, company_id, rating " +
                " FROM rating " +
                " WHERE rating_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, ratingId);
        if (results.next()) {
            rating = mapRowToRating(results);
        }

        return rating;
    }

    @Override
    public List<Rating> getRatingsByStudentId(int studentId) {
        List<Rating> studentRatingList = new ArrayList<>();
        String sql = "SELECT rating_id, matchmaking_id, student_id, company_id, rating " +
                " FROM rating " +
                " WHERE student_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, studentId);
        while (results.next()) {
            studentRatingList.add(mapRowToRating(results));
        }

        return studentRatingList;
    }

    @Override
    public List<Rating> getRatingsByCompanyId(int companyId) {
        List<Rating> companyRatingList = new ArrayList<>();
        String sql = "SELECT rating_id, matchmaking_id, student_id, company_id, rating " +
                " FROM rating " +
                " WHERE company_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, companyId);
        while (results.next()) {
            companyRatingList.add(mapRowToRating(results));
        }

        return companyRatingList;
    }

    @Override
    public List<Rating> getRatingsByMatchmakingId(int matchmakingId) {
        List<Rating> ratingList = new ArrayList<>();
        String sql = "SELECT rating_id, matchmaking_id, student_id, company_id, rating " +
                " FROM rating " +
                " WHERE matchmaking_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, matchmakingId);
        while (results.next()) {
            ratingList.add(mapRowToRating(results));
        }

        return ratingList;
    }

    @Override
    public List<Rating> createSurveyResponses(Rating[] newResponses) {
        List<Rating> newSurveyResponses = new ArrayList<>();
        String sql = "INSERT INTO rating (matchmaking_id, student_id, company_id, rating) " +
                " VALUES (?, ?, ?, ?) RETURNING rating_id;";

        for (Rating response : newResponses) {
            Integer newId = jdbcTemplate.queryForObject(sql, Integer.class, response.getMatchmakingId(),
                    response.getStudentId(), response.getCompanyId(), response.getRating());
            newSurveyResponses.add(getRatingByRatingId(newId));
        }

        return newSurveyResponses;
    }

    @Override
    public void updateSurveyResponses(Rating[] updatedResponses) {
        String sql = "UPDATE rating " +
                " SET matchmaking_id = ?, student_id = ?, company_id = ?, rating = ? " +
                " WHERE rating_id = ?;";

        for(Rating response : updatedResponses) {
            jdbcTemplate.update(sql, response.getMatchmakingId(), response.getStudentId(), response.getCompanyId(),
                    response.getRating(), response.getRatingId());
        }
    }

    private Rating mapRowToRating(SqlRowSet rowSet) {
        Rating rating = new Rating();

        rating.setRatingId(rowSet.getInt("rating_id"));
        rating.setMatchmakingId(rowSet.getInt("matchmaking_id"));
        rating.setStudentId(rowSet.getInt("student_id"));
        rating.setCompanyId(rowSet.getInt("company_id"));
        rating.setRating(rowSet.getInt("rating"));

        return rating;
    }
}
