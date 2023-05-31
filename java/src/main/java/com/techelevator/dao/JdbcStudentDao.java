package com.techelevator.dao;

import com.techelevator.model.Student;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcStudentDao implements StudentDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcStudentDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Student getStudentByUserId(int userId) {
        Student student = null;
        String sql = "SELECT student_id, user_id, first_name, last_name, " +
                " email, linkedin_url, resume_url, te_program_id, pathway_id " +
                " FROM student " +
                " WHERE student.user_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        if (results.next()){
            student = mapRowToStudent(results);
        }

        return student;
    }

    @Override
    public Student getStudentByStudentId(int studentId) {
        Student student = null;
        String sql = "SELECT student_id, user_id, first_name, last_name, " +
                " email, linkedin_url, resume_url, te_program_id, pathway_id " +
                " FROM student " +
                " WHERE student_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, studentId);
        if (results.next()){
            student = mapRowToStudent(results);
        }

        return student;
    }

    @Override
    public List<Student> getStudentsByPathwayId(int pathwayId) {
        List<Student> studentList = new ArrayList<>();
        String sql = "SELECT student_id, user_id, first_name, last_name, " +
                " email, linkedin_url, resume_url, te_program_id, pathway_id " +
                " FROM student " +
                " WHERE pathway_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, pathwayId);
        while (results.next()) {
            studentList.add(mapRowToStudent(results));
        }

        return studentList;
    }

    @Override
    public Student createStudent(Student newStudent) {
        String sql = "INSERT INTO student (user_id, first_name, last_name, email, linkedin_url, resume_url, te_program_id, pathway_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?) returning student_id;";

        Integer newId = jdbcTemplate.queryForObject(sql, Integer.class, newStudent.getUserId(),
                newStudent.getFirstName(), newStudent.getLastName(), newStudent.getEmailAddress(), newStudent.getLinkedinUrl(), newStudent.getResumeUrl(), newStudent.getTeProgramId(), newStudent.getPathwayId());

        return getStudentByUserId(newId);
    }

    @Override
    public void updateStudent(Student updatedStudent) {
        String sql = "UPDATE student " +
                " SET first_name = ?, last_name = ?, email = ?, linkedin_url = ?, resume_url = ? " +
                " WHERE student_id = ?;";

        jdbcTemplate.update(sql, updatedStudent.getFirstName(), updatedStudent.getLastName(),
                updatedStudent.getEmailAddress(), updatedStudent.getLinkedinUrl(),
                updatedStudent.getResumeUrl(), updatedStudent.getStudentId());
    }


//    ************MAY BE UNNECESSARY - KEEPING FOR NOW BUT MAY DELETE LATER

    @Override
    public Student searchStudentByName(String firstNameSearch, String lastNameSearch) {
        Student student = null;
        String sql = "select student_id, user_id, first_name, last_name, email, linkedin_url, resume_url, te_program_id, pathway_id " +
                "from student " +
                "where first_name = ? or last_name = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, firstNameSearch, lastNameSearch);
        if (results.next()) {
            student = mapRowToStudent(results);
        }
        return student;
    }

    @Override
    public List<Student> getStudentsByTEProgramId(int teProgramId) {
        List<Student> studentList = new ArrayList<>();
        String sql = "select student_id, user_id, first_name, last_name, email, linkedin_url, resume_url, te_program_id, pathway_id " +
                "from student " +
                "where te_program_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, teProgramId);
        while (results.next()) {
            studentList.add(mapRowToStudent(results));
        }
        return studentList;
    }

    @Override
    public void addStudentToInterview(int interviewDay, int studentId) {
        String sql = "INSERT INTO public.project_employee( " +
                " project_id, employee_id) " +
                " VALUES (?, ?);";
        jdbcTemplate.update(sql, interviewDay, studentId);
    }

    @Override
    public void addStudentToForm(int formId, int studentId) {
        String sql = "INSERT INTO public.project_employee( " +
                " project_id, employee_id) " +
                " VALUES (?, ?);";
        jdbcTemplate.update(sql, formId, studentId);
    }

    @Override
    public void deleteStudent(int studentId) {
        String sql = "DELETE FROM public.project_employee " +
                " WHERE project_id = ?;";
        jdbcTemplate.update(sql, studentId);
        sql = "DELETE FROM public.project " +
                " WHERE project_id = ?;";
        jdbcTemplate.update(sql, studentId);
    }

    @Override
    public List<Student> findAllStudents() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT student_id, user_id, first_name, last_name, " +
                " email, linkedin_url, resume_url, te_program_id, pathway_id " +
                " FROM student;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            students.add(mapRowToStudent(results));
        }

        return students;
    }


//    *************KEEP METHOD BELOW

    private Student mapRowToStudent(SqlRowSet rowSet) {
        Student student = new Student();

        student.setStudentId(rowSet.getInt("student_id"));
        student.setUserId(rowSet.getInt("user_id"));
        student.setFirstName(rowSet.getString("first_name"));
        student.setLastName(rowSet.getString("last_name"));
        student.setEmailAddress(rowSet.getString("email"));
        student.setLinkedinUrl(rowSet.getString("linkedin_url"));
        student.setResumeUrl(rowSet.getString("resume_url"));
        student.setTeProgramId(rowSet.getInt("te_program_id"));
        student.setPathwayId(rowSet.getInt("pathway_id"));

        return student;
    }


}
