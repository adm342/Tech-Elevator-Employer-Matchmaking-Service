package com.techelevator.dao;

import com.techelevator.model.Employer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcEmployerDao implements EmployerDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcEmployerDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Employer getEmployerByEmployerId(int employerId) {
        Employer employer = null;
        String sql = "SELECT employer_id, user_id, first_name, last_name, company_id, company_team, title, " +
                " email, linkedin_url, is_point_person " +
                " FROM employer " +
                " WHERE employer_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, employerId);
        if (results.next()){
            employer = mapRowToEmployer(results);
        }

        return employer;
    }

    @Override
    public Employer getEmployerByUserId(int userId) {
        Employer employer = null;
        String sql = "SELECT employer_id, user_id, first_name, last_name, company_id, company_team, title, " +
                " email, linkedin_url, is_point_person " +
                " FROM employer " +
                " WHERE user_id = ?";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        if (results.next()){
            employer = mapRowToEmployer(results);
        }

        return employer;
    }

    @Override
    public List<Employer> getAllEmployers() {
        List<Employer> employers = new ArrayList<>();
        String sql = "SELECT employer_id, user_id, first_name, last_name, company_id, company_team, title, " +
                " email, linkedin_url, is_point_person " +
                " FROM employer;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            employers.add(mapRowToEmployer(results));
        }

        return employers;
    }

    @Override
    public List<Employer> getEmployersByCompanyId(int companyId) {
        List<Employer> employerList = new ArrayList<>();
        String sql = "SELECT employer_id, user_id, first_name, last_name, company_id, company_team, title, " +
                " email, linkedin_url, is_point_person " +
                " FROM employer " +
                " WHERE company_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, companyId);
        while (results.next()) {
            employerList.add(mapRowToEmployer(results));
        }

        return employerList;
    }

    @Override
    public Employer createEmployer(Employer newEmployer) {
        String sql = "INSERT INTO employer (user_id, first_name, last_name, company_id, company_team, " +
                "title, email, linkedin_url, is_point_person) " +
                " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING employer_id;";

        Integer newId = jdbcTemplate.queryForObject(sql, Integer.class, newEmployer.getUserId(),
                newEmployer.getFirstName(), newEmployer.getLastName(), newEmployer.getCompanyId(),
                newEmployer.getCompanyTeam(), newEmployer.getTitle(), newEmployer.getEmail(),
                newEmployer.getLinkedinUrl(), newEmployer.isPointPerson());

        return getEmployerByEmployerId(newId);
    }

    @Override
    public void updateEmployer(Employer updatedEmployer) {
        String sql = "UPDATE employer " +
                " SET first_name = ?, last_name = ?, company_id = ?, company_team = ?, title = ?, " +
                " email = ?, linkedin_url = ?, is_point_person = ? " +
                " WHERE employer_id = ?;";

        jdbcTemplate.update(sql, updatedEmployer.getFirstName(), updatedEmployer.getLastName(),
                updatedEmployer.getCompanyId(), updatedEmployer.getCompanyTeam(), updatedEmployer.getTitle(),
                updatedEmployer.getEmail(), updatedEmployer.getLinkedinUrl(), updatedEmployer.isPointPerson(),
                updatedEmployer.getEmployerId());
    }

    private Employer mapRowToEmployer(SqlRowSet rowSet) {
        Employer employer = new Employer();

        employer.setEmployerId(rowSet.getInt("employer_id"));
        employer.setUserId(rowSet.getInt("user_id"));
        employer.setFirstName(rowSet.getString("first_name"));
        employer.setLastName(rowSet.getString("last_name"));
        employer.setCompanyId(rowSet.getInt("company_id"));
        employer.setCompanyTeam(rowSet.getInt("company_team"));
        employer.setTitle(rowSet.getString("title"));
        employer.setEmail(rowSet.getString("email"));
        employer.setLinkedinUrl(rowSet.getString("linkedin_url"));
        employer.setPointPerson(rowSet.getBoolean("is_point_person"));

        return employer;
    }
}
