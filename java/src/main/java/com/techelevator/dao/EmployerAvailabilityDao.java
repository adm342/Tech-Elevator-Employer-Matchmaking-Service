package com.techelevator.dao;

import com.techelevator.model.Employer;
import com.techelevator.model.EmployerAvailability;

import java.util.List;

public interface EmployerAvailabilityDao {

    EmployerAvailability getAvailabilityByEmployerAvailabilityId(int employerAvailabilityId);

    List<EmployerAvailability> getAvailabilityByEmployerId(int employerId);

    List<EmployerAvailability> createAvailability(Employer newAvailability);

    void updateAvailability(Employer updatedAvailability);
}
