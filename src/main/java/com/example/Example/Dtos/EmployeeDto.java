package com.example.Example.Dtos;

import com.example.Example.entities.Department;
import com.example.Example.entities.Employee;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String IdentityNumber;
    private Integer birthOfYear;
    private String userName;
    private String password;
    private String repeatPassword;
    private String email;
    private String department;
    private Long departmentId;
    private Boolean verify = false;
    private Boolean isDeleted = false;

    public static EmployeeDto convertToDto(Employee employee) {
        EmployeeDto dto = new EmployeeDto();

        dto.setId(employee.getId());
        dto.setFirstName(employee.getFirstName());
        dto.setLastName(employee.getLastName());
        dto.setIdentityNumber(employee.getIdentityNumber());
        dto.setBirthOfYear(employee.getBirthOfYear());
        if (employee.getDepartment() != null) {
            dto.setDepartment(employee.getDepartment().getName());
            dto.setDepartmentId(employee.getDepartment().getId());
        }
        dto.setUserName(employee.getUserName());

        dto.setPassword(employee.getPassword());
        dto.setRepeatPassword(employee.getRepeatPassword());
        dto.setEmail(employee.getEmail());
        dto.setVerify(employee.getVerify());
        dto.setIsDeleted(employee.getIsDeleted());
        return dto;
    }

    public static Employee convertToEntity(EmployeeDto dto) {
        Employee employee = new Employee();
        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        employee.setIdentityNumber(dto.getIdentityNumber());
        employee.setBirthOfYear(dto.getBirthOfYear());
        Department department = new Department();
        department.setName(dto.getDepartment());
        employee.setDepartment(department);
        employee.setDepartmentId(dto.getDepartmentId());
        employee.setUserName(dto.getUserName());
        employee.setPassword(dto.getPassword());
        employee.setRepeatPassword(dto.getRepeatPassword());
        employee.setEmail(dto.getEmail());
        employee.setVerify(dto.getVerify());
        employee.setIsDeleted(dto.getIsDeleted());
        return employee;
    }

    public static EmployeeDto converToDtoForUpdate(Employee employee) {
        EmployeeDto dto = new EmployeeDto();
        dto.setId(employee.getId());
        dto.setFirstName(employee.getFirstName());
        dto.setLastName(employee.getLastName());
        dto.setBirthOfYear(employee.getBirthOfYear());
        if (employee.getDepartment() != null) {
            dto.setDepartment(employee.getDepartment().getName());
            dto.setDepartmentId(employee.getDepartment().getId());
        }
        dto.setUserName(employee.getUserName());

        dto.setPassword(employee.getPassword());
        dto.setRepeatPassword(employee.getRepeatPassword());
        dto.setEmail(employee.getEmail());
        dto.setVerify(employee.getVerify());
        dto.setIsDeleted(employee.getIsDeleted());
        return dto;
    }
    public static Employee convertToEntityForUpdate(EmployeeDto dto) {
        Employee employee = new Employee();
        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        employee.setBirthOfYear(dto.getBirthOfYear());
        Department department = new Department();
        department.setName(dto.getDepartment());
        employee.setDepartment(department);
        employee.setDepartmentId(dto.getDepartmentId());
        employee.setUserName(dto.getUserName());
        employee.setPassword(dto.getPassword());
        employee.setRepeatPassword(dto.getRepeatPassword());
        employee.setEmail(dto.getEmail());
        employee.setVerify(dto.getVerify());
        employee.setIsDeleted(dto.getIsDeleted());
        return employee;
    }
    public List<EmployeeDto> convertToDtoList(List<Employee> employees) {
        List<EmployeeDto> dtos = new ArrayList<>();
        for (Employee employee : employees) {
            dtos.add(convertToDto(employee));
        }
        return dtos;
    }

}
