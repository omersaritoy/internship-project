package com.example.Example.Dtos;

import com.example.Example.entities.Department;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDto {
    private Long id;
    private String name;
    private String code;
    private List<EmployeeDto> employees;
    private Boolean isDeleted = false;
    private Boolean isActive=true;

    public static DepartmentDto convertToDto(Department department) {
        DepartmentDto dto = new DepartmentDto();
        dto.setName(department.getName());
        dto.setId(department.getId());
        dto.setCode(department.getCode());
        dto.setIsDeleted(department.getIsDeleted());
        dto.setIsActive(department.getIsActive());
        return dto;
    }

    public static Department convertToEntity(DepartmentDto dto) {
        Department department = new Department();
        department.setName(dto.getName());
        department.setCode(dto.getCode());
        department.setIsDeleted(dto.getIsDeleted());
        department.setIsActive(dto.getIsActive());
        return department;
    }

    public static DepartmentDto convertToDtoList(Department department, List<EmployeeDto> employeesDto) {
        DepartmentDto dto = new DepartmentDto();
        dto.setName(department.getName());
        dto.setId(department.getId());
        dto.setEmployees(employeesDto);
        dto.setCode(department.getCode());

        return dto;
    }
}
