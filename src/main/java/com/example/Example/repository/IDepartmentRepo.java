package com.example.Example.repository;


import com.example.Example.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDepartmentRepo extends JpaRepository<Department, Long> {

}
