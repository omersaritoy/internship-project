package com.example.Example.services;

import com.example.Example.Dtos.GMApprovalDto;
import com.example.Example.utilities.DataResult;
import com.example.Example.utilities.Result;

import java.util.List;

public interface IGMApproveService {

    DataResult<List<GMApprovalDto>> getAll();
    void requestDepartmentChange(Long employeeId, Long departmentId);
    Result approve(Long id,  Long newDepartmentId);


}
