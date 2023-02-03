package com.example.crudspringmongo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class StudentNotFoundException extends Exception{
    public StudentNotFoundException(String id) {
        super("Student not found with ID: " + id);
    }

}
