package com.example.crudspringmongo.controllers;

import com.example.crudspringmongo.exceptions.StudentNotFoundException;
import com.example.crudspringmongo.models.Student;
import com.example.crudspringmongo.services.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.rmi.StubNotFoundException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/student")
public class StudentController {

    private final StudentService studentService;


    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/all")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable(value = "id") String id) throws StudentNotFoundException {
        Optional<Student> user = studentService.getStudentById(id);
        if (user.isPresent()) {
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        } else {
            throw new StudentNotFoundException(id);
        }
    }
}
