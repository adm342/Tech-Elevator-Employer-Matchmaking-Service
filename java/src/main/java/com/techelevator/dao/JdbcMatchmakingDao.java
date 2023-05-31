package com.techelevator.dao;

import com.techelevator.model.MatchmakingEvent;
import com.techelevator.model.MatchmakingSession;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcMatchmakingDao implements MatchmakingDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcMatchmakingDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public MatchmakingEvent getMatchmakingEvent(int matchmakingId) {
        MatchmakingEvent matchmakingEvent = new MatchmakingEvent();
        String sql = "SELECT matchmaking_id, pathway_id, num_of_sessions, start_time, num_of_slots, " +
                " slot_length, slot_break_length, extend_break_slot, extend_break_length, rating_method " +
                " FROM matchmaking " +
                " WHERE matchmaking_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, matchmakingId);
        if (results.next()) {
            matchmakingEvent = mapRowToMatchmakingEvent(results);
        }

        return matchmakingEvent;
    }

    @Override
    public List<MatchmakingEvent> getAllMatchmakingEvents() {
        List<MatchmakingEvent> matchmakingEvents = new ArrayList<>();
        String sql = "SELECT matchmaking_id, pathway_id, num_of_sessions, start_time, num_of_slots, " +
                " slot_length, slot_break_length, extend_break_slot, extend_break_length, rating_method " +
                " FROM matchmaking;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            matchmakingEvents.add(mapRowToMatchmakingEvent(results));
        }

        return matchmakingEvents;
    }

    @Override
    public MatchmakingEvent createMatchmakingEvent(MatchmakingEvent newEvent) {
        String sql = "INSERT INTO matchmaking (pathway_id, num_of_sessions, start_time, num_of_slots, " +
                " slot_length, slot_break_length, extend_break_slot, extend_break_length, rating_method) " +
                " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING matchmaking_id;";

        Integer newId = jdbcTemplate.queryForObject(sql, Integer.class, newEvent.getPathwayId(),
                newEvent.getNumberOfSessions(), newEvent.getStartTime(),
                newEvent.getNumberOfSlots(), newEvent.getSlotDurationInMinutes(),
                newEvent.getSlotBreakDurationInMinutes(), newEvent.getExtendedBreakSlotLocation(),
                newEvent.getExtendedBreakDurationInMinutes(), newEvent.getSurveyMethod());

        return getMatchmakingEvent(newId);
    }

    @Override
    public void updateMatchmakingEvent(MatchmakingEvent updatedEvent) {
        String sql = "UPDATE matchmaking " +
                " SET pathway_id = ?, num_of_sessions = ?, start_time = ?, num_of_slots = ?, slot_length = ?, " +
                " slot_break_length = ?, extend_break_slot = ?, extend_break_length = ?, rating_method = ? " +
                " WHERE matchmaking_id = ?;";

        jdbcTemplate.update(sql, updatedEvent.getPathwayId(), updatedEvent.getNumberOfSessions(),
                updatedEvent.getStartTime(), updatedEvent.getNumberOfSlots(),
                updatedEvent.getSlotDurationInMinutes(), updatedEvent.getSlotBreakDurationInMinutes(),
                updatedEvent.getExtendedBreakSlotLocation(), updatedEvent.getSlotBreakDurationInMinutes(),
                updatedEvent.getSurveyMethod(), updatedEvent.getMatchmakingId());
    }

    @Override
    public MatchmakingSession getMatchmakingSessionById(int sessionId) {
        MatchmakingSession matchmakingSession = new MatchmakingSession();
        String sql = "SELECT mm_session_id, matchmaking_id, mm_session_date, mm_session_number " +
                " FROM matchmaking_session " +
                " WHERE mm_session_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, sessionId);
        if (results.next()) {
            matchmakingSession = mapRowToMatchmakingSession(results);
        }

        return matchmakingSession;
    }

    @Override
    public List<MatchmakingSession> getMatchmakingSessionsByEventId(int matchmakingId) {
        List<MatchmakingSession> listOfSessions = new ArrayList<>();
        String sql = "SELECT mm_session_id, matchmaking_id, mm_session_date, mm_session_number " +
                " FROM matchmaking_session " +
                " WHERE matchmaking_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, matchmakingId);
        while (results.next()) {
            listOfSessions.add(mapRowToMatchmakingSession(results));
        }

        return listOfSessions;
    }

    @Override
    public MatchmakingSession createMatchmakingSession(MatchmakingSession newSession) {
        String sql = "INSERT INTO matchmaking_session (matchmaking_id, mm_session_date, mm_session_number) " +
                " VALUES (?, ?, ?) RETURNING mm_session_id;";

        Integer newId = jdbcTemplate.queryForObject(sql, Integer.class, newSession.getMatchmakingId(),
                newSession.getMmSessionDate(), newSession.getMmSessionNumber());

        return getMatchmakingSessionById(newId);
    }

    @Override
    public void updateMatchmakingSession(MatchmakingSession updatedSession) {
        String sql = "UPDATE matchmaking_session " +
                " SET matchmaking_id = ?, mm_session_date = ?, mm_session_number = ? " +
                " WHERE mm_session_id = ?;";

        jdbcTemplate.update(sql, updatedSession.getMatchmakingId(), updatedSession.getMmSessionDate(),
                updatedSession.getMmSessionNumber());
    }

    private MatchmakingEvent mapRowToMatchmakingEvent(SqlRowSet rowSet) {
        MatchmakingEvent matchmakingEvent = new MatchmakingEvent();

        matchmakingEvent.setMatchmakingId(rowSet.getInt("matchmaking_id"));
        matchmakingEvent.setPathwayId(rowSet.getInt("pathway_id"));
        matchmakingEvent.setNumberOfSessions(rowSet.getInt("num_of_sessions"));
        matchmakingEvent.setStartTime((rowSet.getTime("start_time")).toLocalTime());
        matchmakingEvent.setNumberOfSlots(rowSet.getInt("num_of_slots"));
        matchmakingEvent.setSlotDurationInMinutes(rowSet.getInt("slot_length"));
        matchmakingEvent.setSlotBreakDurationInMinutes(rowSet.getInt("slot_break_length"));
        matchmakingEvent.setExtendedBreakSlotLocation(rowSet.getInt("extend_break_slot"));
        matchmakingEvent.setExtendedBreakDurationInMinutes(rowSet.getInt("extend_break_length"));
        matchmakingEvent.setSurveyMethod(rowSet.getString("rating_method"));

        return matchmakingEvent;
    }

    private MatchmakingSession mapRowToMatchmakingSession(SqlRowSet rowSet) {
        MatchmakingSession matchmakingSession = new MatchmakingSession();

        matchmakingSession.setMmSessionId(rowSet.getInt("mm_session_id"));
        matchmakingSession.setMatchmakingId(rowSet.getInt("matchmaking_id"));
        matchmakingSession.setMmSessionDate(rowSet.getDate("mm_session_date").toLocalDate());
        matchmakingSession.setMmSessionNumber(rowSet.getInt("mm_session_number"));

        return matchmakingSession;
    }
}
