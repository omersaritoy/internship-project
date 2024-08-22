package com.example.Example.Manager;

import com.example.Example.Dtos.GMApprovalDto;
import com.example.Example.entities.Department;
import com.example.Example.entities.Employee;
import com.example.Example.entities.GMApproval;
import com.example.Example.repository.IDepartmentRepo;
import com.example.Example.repository.IEmployeeRepo;
import com.example.Example.repository.IGMApprovalRepo;
import com.example.Example.services.IGMApproveService;
import com.example.Example.utilities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class GMApproveManager implements IGMApproveService {

    private final IGMApprovalRepo gmApprovalRepo;
    private final IDepartmentRepo departmentRepo;
    private final IEmployeeRepo employeeRepo;

    @Autowired
    public GMApproveManager(IGMApprovalRepo gmApprovalRepo, IDepartmentRepo departmentRepo, IEmployeeRepo employeeRepo) {
        this.gmApprovalRepo = gmApprovalRepo;
        this.departmentRepo = departmentRepo;
        this.employeeRepo = employeeRepo;
    }



    @Override
    public DataResult<List<GMApprovalDto>> getAll() {
        List<GMApproval> gmApprovals = gmApprovalRepo.findAll();
        List<GMApprovalDto> gmApprovalDtos = new ArrayList<>();
        for (GMApproval gmApproval : gmApprovals) {
            gmApprovalDtos.add(GMApprovalDto.convertToDto(gmApproval));
        }
        return new SuccessDataResult<>(gmApprovalDtos);
    }

    @Override

    public void requestDepartmentChange(Long employeeId, Long departmentId) {
        Employee employee=employeeRepo.findById(employeeId).orElse(null );
        Department department=departmentRepo.findById(departmentId).orElse(null );
        GMApproval gmApproval=new GMApproval();
        if(employee!=null && department!=null) {
            gmApproval.setEmployeeId(employeeId);
            gmApproval.setRequestTime(LocalDateTime.now());
            gmApproval.setApproved(false);
            gmApproval.setApprovalTime(null);
            gmApprovalRepo.save(gmApproval);
        }
    }

    @Override
    public Result approve(Long id, Long newDepartmentId) {
        GMApproval gmApproval=gmApprovalRepo.findById(id).orElse(null);
        Department department=departmentRepo.findById(newDepartmentId).orElse(null);
        Employee employee=employeeRepo.findById(gmApproval.getEmployeeId()).orElse(null);
        if(employee!=null && department!=null&&gmApproval!=null) {
            employee.setDepartment(department);
            gmApproval.setApproved(true);
            gmApproval.setApprovalTime(LocalDateTime.now());
            gmApprovalRepo.save(gmApproval);
            return new DataResult<>(true,"Department başarılı bir şekilde değiştirildi",GMApprovalDto.convertToDto(gmApproval));
        }

        return new ErrorResult("Veriler boş girildi tekrar deneyiniz");
    }
}
