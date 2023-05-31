package com.techelevator.controller;

import com.techelevator.dao.InterviewDao;
import com.techelevator.dao.MatchmakingDao;
import com.techelevator.dao.RatingDao;
import com.techelevator.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class MatchmakingController {

    private MatchmakingDao matchmakingDao;
    private InterviewDao interviewDao;
    private RatingDao ratingDao;

    public MatchmakingController(MatchmakingDao matchmakingDao, InterviewDao interviewDao, RatingDao ratingDao) {
        this.matchmakingDao = matchmakingDao;
        this.interviewDao = interviewDao;
        this.ratingDao = ratingDao;
    }

    @RequestMapping(path ="/matchmaking", method= RequestMethod.GET)
    public List<MatchmakingEvent> getAllMatchmakingEvents() {
        List<MatchmakingEvent> events = matchmakingDao.getAllMatchmakingEvents();

        for (MatchmakingEvent event : events) {
            event.setMmSessions(matchmakingDao.getMatchmakingSessionsByEventId(event.getMatchmakingId()));

            for (MatchmakingSession session : event.getMmSessions()) {
                session.setSchedule(interviewDao.getInterviewsByMMSessionId(session.getMmSessionId()));
            }
        }
        return events;
    }

    @RequestMapping(path = "/matchmaking/{matchmakingId}", method = RequestMethod.GET)
    public MatchmakingEvent getMatchmakingEventById(@PathVariable int matchmakingId) {
        MatchmakingEvent event = matchmakingDao.getMatchmakingEvent(matchmakingId);
        List<MatchmakingSession> sessions = matchmakingDao.getMatchmakingSessionsByEventId(matchmakingId);
        for (MatchmakingSession session : sessions) {
            session.setSchedule(interviewDao.getInterviewsByMMSessionId(session.getMmSessionId()));
        }
        event.setMmSessions(sessions);
        return event;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/matchmaking", method = RequestMethod.POST)
    public MatchmakingEvent createMatchmakingEvent(@Valid @RequestBody MatchmakingEvent newEvent) {
        return matchmakingDao.createMatchmakingEvent(newEvent);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(path = "/matchmaking/{matchmakingId}", method = RequestMethod.PUT)
    public void updateMatchmakingEvent(@Valid @RequestBody MatchmakingEvent updatedEvent) {
        MatchmakingEvent matchmakingEvent = matchmakingDao.getMatchmakingEvent(updatedEvent.getMatchmakingId());

        if (matchmakingEvent == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found.");
        } else {
            matchmakingDao.updateMatchmakingEvent(updatedEvent);
        }
    }

    @RequestMapping(path ="/matchmaking/sessions/{sessionId}", method= RequestMethod.GET)
    public MatchmakingSession getMatchmakingSessionById(@PathVariable int sessionId) {
        return matchmakingDao.getMatchmakingSessionById(sessionId);
    }

    @RequestMapping(path ="/matchmaking/{matchmakingId}/sessions", method= RequestMethod.GET)
    public List<MatchmakingSession> getMatchmakingSessionsByEventId(@PathVariable int matchmakingId) {
        return matchmakingDao.getMatchmakingSessionsByEventId(matchmakingId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/matchmaking/sessions", method = RequestMethod.POST)
    public MatchmakingSession createMatchmakingSession(@Valid @RequestBody MatchmakingSession newSession) {
        return matchmakingDao.createMatchmakingSession(newSession);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(path = "/matchmaking/sessions/{mmSessionId}", method = RequestMethod.PUT)
    public void updateMatchmakingSession(@Valid @RequestBody MatchmakingSession updatedSession) {
        MatchmakingSession matchmakingSession =
                matchmakingDao.getMatchmakingSessionById(updatedSession.getMmSessionId());

        if (matchmakingSession == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Session not found.");
        } else {
            matchmakingDao.updateMatchmakingSession(updatedSession);
        }
    }

    @RequestMapping(path = "/matchmaking/{matchmakingId}/ratings", method = RequestMethod.GET)
    public List<Rating> getRatingsByMatchmakingId(@PathVariable int matchmakingId) {
        return ratingDao.getRatingsByMatchmakingId(matchmakingId);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'STUDENT')")
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/matchmaking/ratings", method = RequestMethod.POST)
    public List<Rating> createSurveyResponses(@Valid @RequestBody Rating[] newResponses) {
        return ratingDao.createSurveyResponses(newResponses);
    }


    @PreAuthorize("hasAnyRole('ADMIN', 'STUDENT')")
    @RequestMapping(path = "/matchmaking/ratings", method = RequestMethod.PUT)
    public void updateSurveyResponses(@Valid @RequestBody Rating[] updatedResponses) {
        int responseExistsCount = 0;

        for (Rating response : updatedResponses) {
            Rating rating = ratingDao.getRatingByRatingId(response.getRatingId());

            if (rating == null) {
                responseExistsCount += 0;
            } else {
                responseExistsCount++;
            }
        }

        if (responseExistsCount != updatedResponses.length) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ratings not found.");
        } else {
            ratingDao.updateSurveyResponses(updatedResponses);
        }
    }

    @RequestMapping(path = "/matchmaking/{matchmakingId}/interviews", method = RequestMethod.GET)
    public List<Interview> getInterviewsByMatchmakingId(@PathVariable int matchmakingId) {
        return interviewDao.getInterviewsByMatchmakingId(matchmakingId);
    }

    @RequestMapping(path = "/matchmaking/interviews/{interviewId}", method = RequestMethod.GET)
    public Interview getInterviewByInterviewId(@PathVariable int interviewId) {
        return interviewDao.getInterviewByInterviewId(interviewId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/matchmaking/interviews", method = RequestMethod.POST)
    public Interview createInterview(@Valid @RequestBody Interview newInterview) {
        return interviewDao.createInterview(newInterview);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(path = "/matchmaking/interviews", method = RequestMethod.PUT)
    public void updateInterview(@Valid @RequestBody Interview updatedInterview) {
        Interview interview = interviewDao.getInterviewByInterviewId(updatedInterview.getInterviewId());

        if (interview == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Interview not found.");
        } else {
            interviewDao.updateInterview(updatedInterview);
        }
    }

    @PreAuthorize("hasRole('STUDENT')")
    @RequestMapping(path= "/matchmaking/myInterviews", method = RequestMethod.GET)
    public List<InterviewDTO> myInterviews (Principal principal) {
        return interviewDao.getMyInterviews(principal.getName());
    }

}
