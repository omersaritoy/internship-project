package com.example.Example.Manager;

import com.example.Example.Dtos.EmployeeDto;
import com.example.Example.Dtos.GMApprovalDto;
import com.example.Example.entities.Department;
import com.example.Example.entities.Employee;
import com.example.Example.entities.GMApproval;
import com.example.Example.repository.IDepartmentRepo;
import com.example.Example.repository.IEmployeeRepo;
import com.example.Example.repository.IGMApprovalRepo;
import com.example.Example.services.IGMApprovalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class GMApprovalManager implements IGMApprovalService {

    private final IGMApprovalRepo gmApprovalRepo;
    private final IDepartmentRepo departmentRepo;
    private final IEmployeeRepo employeeRepo;

    @Autowired
    public GMApprovalManager(IGMApprovalRepo gmApprovalRepo, IDepartmentRepo departmentRepo, IEmployeeRepo employeeRepo) {
        this.gmApprovalRepo = gmApprovalRepo;
        this.departmentRepo = departmentRepo;
        this.employeeRepo = employeeRepo;
    }

    @Override
    public void RequestDepartmentChange(Long empId, Long newDepId) {
        Employee employee = employeeRepo.findById(empId).get();
        Department department = departmentRepo.findById(newDepId).get();

        if(employee != null && department != null) {
            GMApproval gmApproval = new GMApproval();
            gmApproval.setEmployee(employee);
            gmApproval.setApproved(false);
            gmApproval.setRequestTime(LocalDateTime.now());
            gmApprovalRepo.save(gmApproval);
        }

    }

    @Override
    public void ApproveDepartmentChange(Long empId, Long deptId) {




    }

    @Override
    public List<GMApprovalDto> getAll() {
        List<GMApproval> gmApprovals = gmApprovalRepo.findAll();
        List<GMApprovalDto> gmApprovalDtos=new ArrayList<>();

        for(GMApproval gmApproval : gmApprovals) {
            gmApprovalDtos.add(getDto().convertToDto(gmApproval));
        }
        return gmApprovalDtos;
    }
    public GMApprovalDto getDto() {
        GMApprovalDto gmApprovalDto = new GMApprovalDto();
        return gmApprovalDto;
    }

}
