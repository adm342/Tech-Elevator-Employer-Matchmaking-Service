package com.techelevator.dao;

import com.techelevator.model.Interview;
import com.techelevator.model.InterviewDTO;

import java.util.List;

public interface InterviewDao {

    Interview getInterviewByInterviewId(int interviewId);

    List<Interview> getInterviewsByMatchmakingId(int matchmakingId);

    List<Interview> getInterviewsByMMSessionId(int mmSessionId);

    List<Interview> getInterviewsByStudentId(int studentId);

    List<Interview> getInterviewsByEmployerId(int employerId);

    Interview createInterview(Interview interview);

    void updateInterview(Interview interview);

    List<InterviewDTO> getMyInterviews(String name);
}
