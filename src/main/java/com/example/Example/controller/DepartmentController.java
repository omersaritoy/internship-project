package com.example.Example.controller;

import com.example.Example.Dtos.DepartmentDto;

import com.example.Example.Manager.DepartmentManager;
import com.example.Example.utilities.DataResult;
import com.example.Example.utilities.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.Example.services.IDepartmentService;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {


    private final IDepartmentService departmentService;
    private final DepartmentManager departmentManager;

    @Autowired
    public DepartmentController(IDepartmentService departmentService, DepartmentManager departmentManager) {
        this.departmentService = departmentService;
        this.departmentManager = departmentManager;
    }

    @GetMapping("/findAll")
    public DataResult<List<DepartmentDto>> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @GetMapping("/{id}")
    public DataResult<DepartmentDto> getDepartmentById(@PathVariable Long id) {
        return departmentService.getDepartmentById(id);
    }

    @PostMapping("/create")
    public DataResult<DepartmentDto> createDepartment(@RequestBody DepartmentDto department) {
        return departmentService.saveDepartment(department);
    }

    @DeleteMapping("/{id}")
    public Result deleteDepartment(@PathVariable Long id) {
        return departmentService.deleteDepartment(id);
    }

    @PutMapping("/{id}")
    public DataResult<DepartmentDto> updateDepartment(@PathVariable Long id, @RequestBody DepartmentDto department) {
        return departmentService.updateDepartment(id, department);
    }
    @GetMapping("/employeesCounts")
    public DataResult<List<String>> getEmployees() {
        return departmentManager.getDepartmentEmployeeCounts();
    }

}
