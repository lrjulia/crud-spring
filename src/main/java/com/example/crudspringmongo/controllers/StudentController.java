package com.example.crudspringmongo.controllers;

import com.example.crudspringmongo.exceptions.StudentNotFoundException;
import com.example.crudspringmongo.models.Student;
import com.example.crudspringmongo.services.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.rmi.ServerException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/student")
@Api(value = "Students API REST")
@CrossOrigin(origins = "*")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/all")
    @ApiOperation(value = "Returns all students.")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Returns one student.")
    public ResponseEntity<Student> getStudentById(@PathVariable(value = "id") String id) throws StudentNotFoundException {
        Optional<Student> student = studentService.getStudentById(id);
        if (student.isPresent()) {
            return new ResponseEntity<>(student.get(), HttpStatus.OK);
        } else {
            throw new StudentNotFoundException(id);
        }
    }

    @PostMapping("/add")
    @ApiOperation(value = "Adds a student.")
    public ResponseEntity<Student> addNewStudent(@RequestBody Student newStudent) throws ServerException {
        Student student = studentService.addNewStudent(newStudent);
        if (student == null) {
            throw new ServerException("Error");
        } else {
            return new ResponseEntity<>(student, HttpStatus.CREATED);
        }
    }

    @PutMapping("/update")
    @ApiOperation(value = "Updates a student.")
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
    @ApiOperation(value = "Deletes a student.")
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
