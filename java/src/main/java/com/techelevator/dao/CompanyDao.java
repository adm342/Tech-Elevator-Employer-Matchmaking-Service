package com.techelevator.dao;

import com.techelevator.model.Company;
import com.techelevator.model.Student;

import java.util.List;

public interface CompanyDao {

    Company getCompany(int companyId);

    Company createCompany(Company company);

    void updateCompany(Company company);

    void deleteCompany(int companyId);

    List<Company> findAllCompanies();

    Company searchCompanyByName(String companyName);

    List<Company> getCompanyByPathwayId(int pathwayId);

    void addCompanyToPathway(int pathwayId, int studentId);

    void addCompanyToMatchmakingDay(int matchmakingDay, int companyId);




}
