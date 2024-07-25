package com.example.Example.services;

import com.example.Example.Dtos.DepartmentDto;

import com.example.Example.utilities.DataResult;
import com.example.Example.utilities.Result;

import java.util.List;

public interface IDepartmentService {

    DataResult<List<DepartmentDto>> getAllDepartments();

    DataResult<DepartmentDto> getDepartmentById(Long id);

    DataResult<DepartmentDto> saveDepartment(DepartmentDto department);

    Result deleteDepartment(Long id);

    DataResult<DepartmentDto> updateDepartment(Long id, DepartmentDto department);

}
