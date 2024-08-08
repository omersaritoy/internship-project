package com.example.Example.controller;

import com.example.Example.Dtos.EmployeeDto;
import com.example.Example.services.IEmployeeService;
import com.example.Example.utilities.DataResult;
import com.example.Example.utilities.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin("*")
public class EmployeeController {


    private final IEmployeeService employeeService;

    @Autowired
    public EmployeeController(IEmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/findAll")
    public DataResult<List<EmployeeDto>> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public DataResult<EmployeeDto> getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }
    @GetMapping("/findByUserName/{userName}")
    public Result getEmployeeByUserName(@PathVariable String userName) {
        return employeeService.getEmployeeByUserName(userName);
    }
    @PostMapping("/create")
    public Result createEmployee(@RequestBody EmployeeDto employee) throws Exception {
        return employeeService.saveEmployee(employee);
    }

    @PutMapping("/{id}")
    public Result updateEmployee(@RequestBody EmployeeDto employee,@PathVariable Long id ) {
        return employeeService.updateEmployee(employee, id);
    }

    @DeleteMapping("/{id}")
    public Result deleteEmployee(@PathVariable Long id) {
        return employeeService.deleteEmployee(id);
    }

    @PutMapping("/verify/{id}")
    public Result verifyEmployee(@PathVariable Long id, @RequestParam String verificationCode) {
        return employeeService.verifyEmployee(id, verificationCode);
    }
}
