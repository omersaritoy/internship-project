package com.example.Example;

import com.example.Example.entities.Employee;
import com.example.Example.repository.IEmployeeRepo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.io.IOException;
import java.net.*;

@SpringBootApplication
public class ExampleApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(ExampleApplication.class, args);
	}

}
