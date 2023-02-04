package com.example.crudspringmongo.controllers;

import com.example.crudspringmongo.exceptions.StudentNotFoundException;
import com.example.crudspringmongo.models.Student;
import com.example.crudspringmongo.services.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.rmi.ServerException;
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
        Optional<Student> student = studentService.getStudentById(id);
        if (student.isPresent()) {
            return new ResponseEntity<>(student.get(), HttpStatus.OK);
        } else {
            throw new StudentNotFoundException(id);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Student> addNewStudent(@RequestBody Student newStudent) throws ServerException {
        Student student = studentService.addNewStudent(newStudent);
        if (student == null) {
            throw new ServerException("Error");
        } else {
            return new ResponseEntity<>(student, HttpStatus.CREATED);
        }
    }

}
