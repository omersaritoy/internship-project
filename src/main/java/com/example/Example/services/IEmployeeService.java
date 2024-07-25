package com.example.Example.services;

import com.example.Example.Dtos.EmployeeDto;

import com.example.Example.utilities.DataResult;
import com.example.Example.utilities.Result;


import java.util.List;

public interface IEmployeeService {
    DataResult<EmployeeDto> getEmployeeById(Long id);

    DataResult<List<EmployeeDto>> getAllEmployees();

    Result saveEmployee(EmployeeDto employee);

    Result updateEmployee(EmployeeDto employee, Long id);

    Result deleteEmployee(Long id);

    Result verifyEmployee(Long id, String verificationCode);

    Result getEmployeeByUserName(String userName);
}
