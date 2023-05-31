package com.techelevator.dao;

import com.techelevator.model.MatchmakingEvent;
import com.techelevator.model.MatchmakingSession;

import java.util.List;

public interface MatchmakingDao {

    MatchmakingEvent getMatchmakingEvent(int matchmakingId);

    List<MatchmakingEvent> getAllMatchmakingEvents();

    MatchmakingEvent createMatchmakingEvent(MatchmakingEvent matchmakingEvent);

    void updateMatchmakingEvent(MatchmakingEvent matchmakingEvent);

    MatchmakingSession getMatchmakingSessionById(int sessionId);

    List<MatchmakingSession> getMatchmakingSessionsByEventId(int matchmakingId);

    MatchmakingSession createMatchmakingSession(MatchmakingSession matchmakingSession);

    void updateMatchmakingSession(MatchmakingSession matchmakingSession);

}
