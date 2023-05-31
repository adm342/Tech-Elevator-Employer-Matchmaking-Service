package com.techelevator.dao;

import com.techelevator.model.Company;
import com.techelevator.model.Student;

import java.security.Principal;
import java.util.List;

public interface StudentDao {

    Student createStudent(Student student);

    void updateStudent(Student student);

    void deleteStudent(int studentId);

    List<Student> findAllStudents();

    Student getStudentByStudentId(int studentId);

    Student getStudentByUserId(int userId);

    Student searchStudentByName(String firstNameSearch, String lastNameSearch);

    List<Student> getStudentsByPathwayId(int pathwayId);

    List<Student> getStudentsByTEProgramId(int teProgramId);

    void addStudentToInterview(int interviewDay, int studentId);

    void addStudentToForm(int formId, int studentId);




}
