package com.example.crudspringmongo;

import com.example.crudspringmongo.models.Address;
import com.example.crudspringmongo.models.Gender;
import com.example.crudspringmongo.models.Student;
import com.example.crudspringmongo.repositories.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

@SpringBootApplication
public class CrudSpringMongoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudSpringMongoApplication.class, args);
	}

}
