package com.example.crudspringmongo.repositories;

import com.example.crudspringmongo.models.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository extends MongoRepository<Student, String> {
}
