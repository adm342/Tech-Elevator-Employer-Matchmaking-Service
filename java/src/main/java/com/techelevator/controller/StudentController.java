package com.techelevator.controller;

import com.techelevator.dao.StudentDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Student;
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
@RequestMapping("/students")
public class StudentController {

    private StudentDao studentDao;
    private UserDao userDao;

    public StudentController(StudentDao studentDao, UserDao userDao) {
        this.studentDao = studentDao;
        this.userDao = userDao;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Student> getAllStudents() {
        return studentDao.findAllStudents();
    }

    @RequestMapping(path="/{studentId}", method = RequestMethod.GET)
    public Student getStudentById(@PathVariable int studentId) {
        return studentDao.getStudentByStudentId(studentId);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'STUDENT')")
    @RequestMapping(path="/home", method = RequestMethod.GET)
    public Student getStudentByUserId(Principal currentUser) {
        return studentDao.getStudentByUserId(userDao.findIdByUsername(currentUser.getName()));
    }

    @RequestMapping(path="/{pathwayId}", method = RequestMethod.GET)
    public List<Student> getStudentByPathwayId(@PathVariable int pathwayId) {
        return studentDao.getStudentsByPathwayId(pathwayId);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'STUDENT')")
    @RequestMapping(path = "", method = RequestMethod.PUT)
    public void updateStudent(@Valid @RequestBody Student updatedStudent) {
        Student student = studentDao.getStudentByStudentId(updatedStudent.getStudentId());

        if (student == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found.");
        } else {
            studentDao.updateStudent(updatedStudent);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "", method = RequestMethod.POST)
    public Student createStudent(@Valid @RequestBody Student newStudent) {
        return studentDao.createStudent(newStudent);
    }

}
