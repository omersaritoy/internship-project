package com.example.Example.annotations;


import com.example.Example.Dtos.DepartmentDto;
import com.example.Example.Dtos.EmployeeDto;
import com.example.Example.Manager.EmployeeManager;
import com.example.Example.entities.Department;
import com.example.Example.entities.Employee;
import com.example.Example.repository.IDepartmentRepo;
import com.example.Example.repository.IEmployeeRepo;
import com.example.Example.utilities.DataResult;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Aspect
@Component
public class UniqueAspect {

    @Autowired
    private IDepartmentRepo departmentRepo;
    @Autowired
    private IEmployeeRepo employeeRepo;
    @Autowired
    private EmployeeManager employeeManager;

    // @Around anotasyonu, belirli bir noktadan önce ve sonra işlemler yapabileceğimizi belirtir.
    // @annotation(Unique), @Unique anotasyonu ile işaretlenmiş metotların etrafında bu işlemi yapar.


    @Around("@annotation(unique)")
    public Object checkUnique(ProceedingJoinPoint joinPoint, Unique unique) throws Throwable {
        Object[] args = joinPoint.getArgs();

        Map<String, Object> argsMap = new HashMap<>();
        for (Object arg : args) {

            argsMap.put(arg.getClass().getSimpleName(), arg);

        }

        EmployeeDto employeeDto;
        DepartmentDto dto;
        boolean isUnique = true;
        String errorMessage = "";
        for (UniqueType type : unique.value()) {
            switch (type) {
                case UserName:
                    employeeDto = (EmployeeDto) args[0];
                    if (!checkingUserName(employeeDto.getUserName())) {
                        isUnique = false;
                        errorMessage = "User name must be unique";
                    }
                    break;
                case UserNameForUpdate:
                    employeeDto = (EmployeeDto) args[0];
                    Employee emp = employeeRepo.findByIdentityNumber(employeeDto.getIdentityNumber());
                    List<Employee> employees = employeeRepo.findAll();
                    Employee existEmployee=null;
                    for (Employee employee : employees) {
                        if (employee.getUserName().equals(employeeDto.getUserName()) && !emp.getId().equals(employee.getId())) {
                            existEmployee=employee;
                            break;
                        }
                    }
                    if (existEmployee != null && !existEmployee.getId().equals(employeeDto.getId())) {
                        isUnique = false;
                        errorMessage = "User name must be unique";
                    }

                    break;

                case Email:
                    employeeDto = (EmployeeDto) args[0];
                    if (!checkingEmail(employeeDto.getEmail())) {
                        isUnique = false;
                        errorMessage = "Email must be unique";
                    }
                    break;
                case EmailForUpdate:
                    employeeDto = (EmployeeDto) args[0];
                    Employee empl=employeeRepo.findByIdentityNumber(employeeDto.getIdentityNumber());
                    Employee existEmp =null;
                    List<Employee> emps=employeeRepo.findAll();
                    for(Employee employee : emps){
                        if(employee.getEmail().equals(employeeDto.getEmail())&&!empl.getId().equals(employee.getId())){
                            existEmp=employee;
                            break;
                        }
                    }

                    if (existEmp != null && !existEmp.getId().equals(employeeDto.getId())) {
                        isUnique = false;
                        errorMessage = "Email must be unique";
                    }
                    break;
                case Code:
                    dto = (DepartmentDto) argsMap.get("DepartmentDto");
                    Department department = DepartmentDto.convertToEntity(dto);
                    if (department != null && !checkCode(department.getCode())) {
                        isUnique = false;
                        errorMessage = "Department code must be unique";
                    }
                    break;
            }
        }

        if (!isUnique) {
            return new DataResult<>(false, errorMessage);
        }

        return joinPoint.proceed();
    }

    public boolean checkingEmail(String email) {
        List<Employee> employees = employeeRepo.findAll();

        for (Employee employee : employees) {
            if (employee.getEmail().equals(email)) {
                return false;
            }
        }
        return true;
    }

    public boolean checkingUserName(String userName) {
        List<Employee> employees = employeeRepo.findAll();
        for (Employee employee : employees) {
            if (employee.getUserName().equals(userName)) {
                return false;
            }
        }
        return true;
    }

    public boolean checkCode(String code) {
        List<Department> departments = departmentRepo.findAll();
        for (Department department : departments) {
            if (department.getCode().equals(code)) {
                return false;
            }
        }
        return true;
    }
}
