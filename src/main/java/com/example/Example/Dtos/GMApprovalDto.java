package com.example.Example.Dtos;

import com.example.Example.entities.Employee;
import com.example.Example.entities.GMApproval;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class GMApprovalDto {
    private Long id;
    private Long employeeId;
    private boolean approved;
    private LocalDateTime requestTime;
    private LocalDateTime approvalTime;


    public static GMApprovalDto convertToDto(GMApproval gmApproval) {
        GMApprovalDto dto=new GMApprovalDto();
        dto.setId(gmApproval.getId());
        dto.setApproved(gmApproval.isApproved());
        dto.setRequestTime(gmApproval.getRequestTime());
        dto.setApprovalTime(gmApproval.getApprovalTime());
        dto.setEmployeeId(gmApproval.getEmployeeId());
        return dto;
    }
    public static GMApproval convertToEntity(GMApprovalDto dto) {
        GMApproval gmApproval=new GMApproval();
        gmApproval.setId(dto.getId());
        gmApproval.setApproved(dto.isApproved());
        gmApproval.setRequestTime(dto.getRequestTime());
        gmApproval.setApprovalTime(dto.getApprovalTime());
        gmApproval.setEmployeeId(dto.getEmployeeId());
        return gmApproval;
    }
}

