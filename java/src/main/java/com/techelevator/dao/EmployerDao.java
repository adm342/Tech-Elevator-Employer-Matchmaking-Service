package com.techelevator.dao;

import com.techelevator.model.Employer;

import java.util.List;

public interface EmployerDao {

    Employer getEmployerByEmployerId(int employerId);

    Employer getEmployerByUserId(int userId);

    List<Employer> getAllEmployers();

    List<Employer> getEmployersByCompanyId(int companyId);

    Employer createEmployer(Employer employer);

    void updateEmployer(Employer employer);

}
