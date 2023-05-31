package com.techelevator.dao;

import com.techelevator.model.Company;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcCompanyDao  implements CompanyDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcCompanyDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Company getCompany(int companyId) {
        Company company = null;
        String sql = "SELECT company_id, name, website_url, description, position_url, youtube_url, " +
                " image_url, num_of_teams " +
                " FROM company  " +
                " WHERE company_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, companyId);
        if (results.next()){
            company = mapRowToCompany(results);
        }

        return company;
    }

    @Override
    public Company createCompany(Company newCompany) {
        String sql = "INSERT INTO company (name, website_url, description, position_url, youtube_url, " +
                " image_url, num_of_teams) " +
                " VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING company_id;";

        Integer newId = jdbcTemplate.queryForObject(sql, Integer.class, newCompany.getCompanyName(),
                newCompany.getWebsiteUrl(), newCompany.getDescription(), newCompany.getJobPostingUrl(),
                newCompany.getYoutubeLinkUrl(), newCompany.getImageUrl(), newCompany.getNumberOfTeams());

        return getCompany(newId);
    }

    @Override
    public void updateCompany(Company updatedCompany) {
        String sql = "UPDATE company " +
                " SET name = ?, website_url = ?, description = ?, position_url = ?, " +
                " youtube_url = ?, image_url = ?, num_of_teams = ? " +
                " WHERE company_id = ?;";

        jdbcTemplate.update(sql, updatedCompany.getCompanyName(), updatedCompany.getWebsiteUrl(),
                updatedCompany.getDescription(), updatedCompany.getJobPostingUrl(),
                updatedCompany.getYoutubeLinkUrl(), updatedCompany.getImageUrl(),
                updatedCompany.getNumberOfTeams(), updatedCompany.getCompanyId());
    }

    @Override
    public void deleteCompany(int companyId) {
        String sql = "DELETE FROM company " +
                " WHERE company_id = ?";

        jdbcTemplate.update(sql, companyId);
    }

    @Override
    public List<Company> findAllCompanies() {
        List<Company> companies = new ArrayList<>();
        String sql = "SELECT company_id, name, website_url, description, position_url, youtube_url, " +
                " image_url, num_of_teams " +
                " FROM company;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            companies.add(mapRowToCompany(results));
        }

        return companies;
    }

    @Override
    public Company searchCompanyByName(String companyName) {
        Company company = null;
        String sql = "SELECT company_id, name, website_url, description, position_url, youtube_url, " +
                " image_url, num_of_teams " +
                " FROM company " +
                " WHERE name = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, companyName);
        if (results.next()) {
            company = mapRowToCompany(results);
        }

        return company;
    }

    @Override
    public List<Company> getCompanyByPathwayId(int pathwayId) {
        List<Company> companyList = new ArrayList<>();
        String sql = "SELECT company_id, name, website_url, description, position_url, youtube_url, " +
                " image_url, num_of_teams " +
                " FROM company  " +
                " WHERE pathway_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, pathwayId);
        while (results.next()) {
            companyList.add(mapRowToCompany(results));
        }

        return companyList;
    }

    @Override
    public void addCompanyToPathway(int pathwayId, int companyId) {
        String sql = "INSERT INTO public.project_employee( " +
                " project_id, employee_id) " +
                " VALUES (?, ?);";

        jdbcTemplate.update(sql, pathwayId, companyId);
    }

    @Override
    public void addCompanyToMatchmakingDay(int matchmakingDay, int companyId) {
        String sql = "INSERT INTO public.project_employee " +
                " (project_id, employee_id) " +
                " VALUES (?, ?);";

        jdbcTemplate.update(sql, matchmakingDay, companyId);
    }

    private Company mapRowToCompany(SqlRowSet rowSet) {
        Company company = new Company();

        company.setCompanyId(rowSet.getInt("company_id"));
        company.setCompanyName(rowSet.getString("name"));
        company.setWebsiteUrl(rowSet.getString("website_url"));
        company.setDescription(rowSet.getString("description"));
        company.setJobPostingUrl(rowSet.getString("position_url"));
        company.setYoutubeLinkUrl(rowSet.getString("youtube_url"));
        company.setImageUrl(rowSet.getString("image_url"));
        company.setNumberOfTeams(rowSet.getInt("num_of_teams"));

        return company;
    }
}
