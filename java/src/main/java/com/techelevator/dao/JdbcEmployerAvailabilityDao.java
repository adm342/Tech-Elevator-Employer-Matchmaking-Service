package com.techelevator.dao;

import com.techelevator.model.Employer;
import com.techelevator.model.EmployerAvailability;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcEmployerAvailabilityDao implements EmployerAvailabilityDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcEmployerAvailabilityDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public EmployerAvailability getAvailabilityByEmployerAvailabilityId(int employerAvailabilityId) {
        EmployerAvailability employerAvailability = null;
        String sql = "SELECT employer_availability_id, employer_id, date_available, start_time, end_time " +
                " FROM employer_availability " +
                " WHERE employer_availability_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, employerAvailabilityId);
        if (results.next()) {
            employerAvailability = mapRowToEmployerAvailability(results);
        }

        return employerAvailability;
    }

    @Override
    public List<EmployerAvailability> getAvailabilityByEmployerId(int employerId) {
        List<EmployerAvailability> availabilities = new ArrayList<>();
        String sql = "SELECT employer_availability_id, employer_id, date_available, start_time, end_time " +
                " FROM employer_availability " +
                " WHERE employer_id = ? " +
                " ORDER BY date_available ASC;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, employerId);
        while (results.next()) {
            availabilities.add(mapRowToEmployerAvailability(results));
        }

        return availabilities;
    }

    @Override
    public List<EmployerAvailability> createAvailability(Employer newAvailability) {
        List<EmployerAvailability> newAvailabilities = new ArrayList<>();
        String sql = "INSERT INTO employer_availability( " +
                " employer_id, date_available, start_time, end_time) " +
                " VALUES (?, ?, ?, ?) RETURNING employer_availability_id;";

        for(EmployerAvailability day : newAvailability.getAvailabilities()) {
            Integer newId = jdbcTemplate.queryForObject(sql, Integer.class, day.getEmployerId(),
                    day.getDateAvailable(), day.getStartTime(), day.getEndTime());
            newAvailabilities.add(getAvailabilityByEmployerAvailabilityId(newId));
        }

        return newAvailabilities;
    }

    @Override
    public void updateAvailability(Employer updatedAvailability) {
        String sql = "UPDATE employer_availability " +
                " SET date_available = ?, start_time = ?, end_time = ? " +
                " WHERE employer_availability_id = ?;";

        for(EmployerAvailability day : updatedAvailability.getAvailabilities()) {
            jdbcTemplate.update(sql, day.getDateAvailable(), day.getStartTime(), day.getEndTime(),
                    day.getEmployerAvailabilityId());
        }
    }

    private EmployerAvailability mapRowToEmployerAvailability(SqlRowSet rowSet) {
        EmployerAvailability employerAvailability = new EmployerAvailability();

        employerAvailability.setEmployerAvailabilityId(rowSet.getInt("employer_availability_id"));
        employerAvailability.setEmployerId(rowSet.getInt("employer_id"));
        employerAvailability.setDateAvailable(rowSet.getDate("date_available").toLocalDate());
        employerAvailability.setStartTime(rowSet.getTime("start_time").toLocalTime());
        employerAvailability.setEndTime(rowSet.getTime("end_time").toLocalTime());

        return employerAvailability;
    }
}
