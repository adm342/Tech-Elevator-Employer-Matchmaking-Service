package com.techelevator.controller;

import com.techelevator.dao.CompanyDao;
import com.techelevator.dao.EmployerAvailabilityDao;
import com.techelevator.dao.EmployerDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Company;
import com.techelevator.model.Employer;
import com.techelevator.model.EmployerAvailability;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
@RequestMapping("/companies")
public class CompanyController {

    private CompanyDao companyDao;
    private EmployerDao employerDao;
    private EmployerAvailabilityDao employerAvailabilityDao;
    private UserDao userDao;

    public CompanyController(CompanyDao companyDao, EmployerDao employerDao,
                             EmployerAvailabilityDao employerAvailabilityDao, UserDao userDao) {
        this.companyDao = companyDao;
        this.employerDao = employerDao;
        this.employerAvailabilityDao = employerAvailabilityDao;
        this.userDao = userDao;
    }


//    JDBC COMPANY DAO METHODS

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<Company> getAllCompanies() {
        return companyDao.findAllCompanies();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYER')")
    @RequestMapping(path = "/home", method = RequestMethod.GET)
    public Company getCompanyByUserId(Principal currentUser) {
        Employer employer = employerDao.getEmployerByUserId(userDao.findIdByUsername(currentUser.getName()));
        return companyDao.getCompany(employer.getCompanyId());
    }

    @RequestMapping(path = "/{companyId}", method = RequestMethod.GET)
    public Company getCompanyByCompanyId(@PathVariable int companyId) {
        return companyDao.getCompany(companyId);
    }

    @RequestMapping(path ="/pathway/{pathwayId}", method = RequestMethod.GET)
    public List<Company> getCompaniesByPathwayId(@PathVariable int pathwayId) {
        return companyDao.getCompanyByPathwayId(pathwayId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "", method = RequestMethod.POST)
    public Company createCompany(@Valid @RequestBody Company newCompany) {
        return companyDao.createCompany(newCompany);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYER')")
    @RequestMapping(path = "/{companyId}", method = RequestMethod.PUT)
    public void updateCompany(@Valid @RequestBody Company updatedCompany) {
        Company company = companyDao.getCompany(updatedCompany.getCompanyId());

        if (company == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Company not found.");
        } else {
            companyDao.updateCompany(updatedCompany);
        }
    }


//    JDBC EMPLOYER DAO METHODS

    @RequestMapping(path = "/{companyId}/employers", method = RequestMethod.GET)
    public List<Employer> getEmployersByCompanyId(@PathVariable int companyId) {
        List<Employer> employersList = employerDao.getEmployersByCompanyId(companyId);

        return employersList;
    }

    @RequestMapping(path = "/{companyId}/employers/{employerId}", method = RequestMethod.GET)
    public Employer getEmployerByEmployerId(@PathVariable int employerId) {
        Employer employer = employerDao.getEmployerByEmployerId(employerId);

        return employer;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYER')")
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/employers", method = RequestMethod.POST)
    public Employer createEmployer(@Valid @RequestBody Employer newEmployer) {
        return employerDao.createEmployer(newEmployer);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYER')")
    @RequestMapping(path = "/employers/{employerId}", method = RequestMethod.PUT)
    public void updateEmployer(@Valid @RequestBody Employer updatedEmployer) {
        Employer employer = employerDao.getEmployerByEmployerId(updatedEmployer.getEmployerId());

        if (employer == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employer not found.");
        } else {
            employerDao.updateEmployer(updatedEmployer);
        }
    }


//    JDBC EMPLOYER AVAILABILITY METHODS

    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYER')")
    @RequestMapping(path = "/employers/{employerId}/schedule", method = RequestMethod.GET)
    public List<EmployerAvailability> getAvailabilityByEmployerId(@PathVariable int employerId) {
        return employerAvailabilityDao.getAvailabilityByEmployerId(employerId);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYER')")
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/employers/schedule", method = RequestMethod.POST)
    public List<EmployerAvailability> createEmployerAvailability(@Valid @RequestBody Employer newAvailability) {
        return employerAvailabilityDao.createAvailability(newAvailability);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYER')")
    @RequestMapping(path = "/employers/schedule/{employerAvailabilityId}", method = RequestMethod.PUT)
    public void updateEmployerAvailability(@Valid @RequestBody Employer updatedAvailability) {
        List<EmployerAvailability> availabilities =
                employerAvailabilityDao.getAvailabilityByEmployerId(updatedAvailability.getEmployerId());

        if (availabilities == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employer schedule not found.");
        } else {
            employerAvailabilityDao.updateAvailability(updatedAvailability);
        }
    }
}
