package com.techelevator.dao;

import com.techelevator.model.Interview;
import com.techelevator.model.InterviewDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcInterviewDao implements InterviewDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcInterviewDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Interview getInterviewByInterviewId(int interviewId) {
        Interview interview = null;
        String sql = "SELECT interview_id, matchmaking_id, mm_session_id, student_id, employer_id, " +
                " slot, location " +
                " FROM interview " +
                " WHERE interview_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, interviewId);
        if (results.next()) {
            interview = mapRowToInterview(results);
        }

        return interview;
    }

    @Override
    public List<Interview> getInterviewsByMatchmakingId(int matchmakingId) {
        List<Interview> interviews = new ArrayList<>();
        String sql = "SELECT interview_id, matchmaking_id, mm_session_id, student_id, employer_id, " +
                " slot, location " +
                " FROM interview " +
                " WHERE matchmaking_id = ? " +
                " ORDER BY slot ASC;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, matchmakingId);
        while (results.next()) {
            interviews.add(mapRowToInterview(results));
        }

        return interviews;
    }

    @Override
    public List<Interview> getInterviewsByMMSessionId(int mmSessionId) {
        List<Interview> interviews = new ArrayList<>();
        String sql = "SELECT interview_id, matchmaking_id, mm_session_id, student_id, employer_id, " +
                " slot, location " +
                " FROM interview " +
                " WHERE mm_session_id = ? " +
                " ORDER BY slot ASC;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, mmSessionId);
        while (results.next()) {
            interviews.add(mapRowToInterview(results));
        }

        return interviews;
    }

    @Override
    public List<Interview> getInterviewsByStudentId(int studentId) {
        List<Interview> interviews = new ArrayList<>();
        String sql = "SELECT interview_id, matchmaking_id, mm_session_id, student_id, employer_id, " +
                " slot, location " +
                " FROM interview " +
                " WHERE student_id = ? " +
                " ORDER BY slot ASC;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, studentId);
        while (results.next()) {
            interviews.add(mapRowToInterview(results));
        }

        return interviews;
    }

    @Override
    public List<Interview> getInterviewsByEmployerId(int employerId) {
        List<Interview> interviews = new ArrayList<>();
        String sql = "SELECT interview_id, matchmaking_id, mm_session_id, student_id, employer_id, " +
                " slot, location " +
                " FROM interview " +
                " WHERE employer_id = ? " +
                " ORDER BY slot ASC;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, employerId);
        while (results.next()) {
            interviews.add(mapRowToInterview(results));
        }

        return interviews;
    }

    @Override
    public Interview createInterview(Interview newInterview) {
        String sql = "INSERT INTO interview (matchmaking_id, mm_session_id, student_id, employer_id, " +
                " slot, location) " +
                " VALUES (?, ?, ?, ?, ?, ?) RETURNING interview_id;";

        Integer newId = jdbcTemplate.queryForObject(sql, Integer.class, newInterview.getMatchmakingId(),
                newInterview.getMmSessionId(), newInterview.getStudentId(), newInterview.getEmployerId(),
                newInterview.getSlot(), newInterview.getLocation());

        return getInterviewByInterviewId(newId);
    }

    @Override
    public void updateInterview(Interview updatedInterview) {
        String sql = "UPDATE interview " +
                " SET matchmaking_id = ?, mm_session_id = ?, student_id = ?, employer_id = ?, slot = ?, " +
                " location = ? " +
                " WHERE interview_id = ?;";

        jdbcTemplate.update(sql, updatedInterview.getMatchmakingId(), updatedInterview.getStudentId(),
                updatedInterview.getEmployerId(), updatedInterview.getSlot(),
                updatedInterview.getLocation(), updatedInterview.getInterviewId());
    }

    @Override
    public List<InterviewDTO> getMyInterviews(String name) {
        String sql = "SELECT interview.interview_id, interview.matchmaking_id, interview.mm_session_id, interview.student_id, interview.employer_id, interview.slot, interview.location,\n" +
                "\n" +
                "               (matchmaking.start_time + ((((interview.slot-1) * (matchmaking.slot_length + matchmaking.slot_break_length)))::text || ' minutes')::interval) as interview_start_time,\n" +
                "               (matchmaking.start_time + ((((interview.slot*matchmaking.slot_length) + ((interview.slot-1)* matchmaking.slot_break_length)))::text || ' minutes')::interval) as interview_end_time,\n" +
                "               company.name, matchmaking_session.mm_session_date\n" +
                "                FROM public.interview\n" +
                "                Join matchmaking on interview.matchmaking_id=matchmaking.matchmaking_id\n" +
                "                Join employer on employer.employer_id = interview.employer_id\n" +
                "                Join company on company.company_id = employer.company_id\n" +
                "\t\t\t\tJoin matchmaking_session on matchmaking_session.mm_session_id = interview.mm_session_id\n" +
                "                WHERE interview.student_id = (select student_id from student where user_id = (select user_id from users where username = ?))\n";

        List<InterviewDTO> interviewDTOList = new ArrayList<>();
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, name);
        while (results.next()) {
            interviewDTOList.add(mapRowToInterviewDTO(results));
        }
        return interviewDTOList;
    }

    private Interview mapRowToInterview(SqlRowSet rowSet) {
        Interview interview = new Interview();

        interview.setInterviewId(rowSet.getInt("interview_id"));
        interview.setMatchmakingId(rowSet.getInt("matchmaking_id"));
        interview.setMmSessionId(rowSet.getInt("mm_session_id"));
        interview.setStudentId(rowSet.getInt("student_id"));
        interview.setEmployerId(rowSet.getInt("employer_id"));
        interview.setSlot(rowSet.getInt("slot"));
        interview.setLocation(rowSet.getString("location"));

        return interview;
    }
        private InterviewDTO mapRowToInterviewDTO(SqlRowSet rowSet) {
            InterviewDTO interview = new InterviewDTO();

            interview.setInterviewId(rowSet.getInt("interview_id"));
            interview.setMatchmakingId(rowSet.getInt("matchmaking_id"));
            interview.setMmSessionId(rowSet.getInt("mm_session_id"));
            interview.setStudentId(rowSet.getInt("student_id"));
            interview.setEmployerId(rowSet.getInt("employer_id"));
            interview.setSlot(rowSet.getInt("slot"));
            interview.setLocation(rowSet.getString("location"));
            interview.setStartTime(rowSet.getTime("interview_start_time").toLocalTime());
            interview.setEndTime(rowSet.getTime("interview_end_time").toLocalTime());
            interview.setCompanyName(rowSet.getString("name"));
            interview.setDate(rowSet.getDate("mm_session_date").toLocalDate());

            return interview;
    }
}
