package com.example.crudspringmongo.controllers;

import com.example.crudspringmongo.exceptions.StudentNotFoundException;
import com.example.crudspringmongo.models.Student;
import com.example.crudspringmongo.services.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.rmi.ServerException;
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

    @PutMapping("/update")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) throws StudentNotFoundException {
        Optional<Student> s = studentService.getStudentById(student.getId());
        if (s.isPresent()) {
            Student updatedStudent = studentService.updateStudent(student);
            return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
        } else {
            throw new StudentNotFoundException(student.getId());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable(value = "id") String id) throws StudentNotFoundException {
        Optional<Student> student = studentService.getStudentById(id);
        if (student.isPresent()) {
            studentService.deleteStudent(student.get());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            throw new StudentNotFoundException(id);
        }
    }

}
