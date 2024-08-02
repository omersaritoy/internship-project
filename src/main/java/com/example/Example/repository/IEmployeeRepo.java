package com.example.Example.repository;


import com.example.Example.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEmployeeRepo extends JpaRepository<Employee, Long> {
    Employee findByUserName(String userName);
    Employee findByEmail(String email);
}
