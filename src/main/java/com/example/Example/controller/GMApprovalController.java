package com.example.Example.controller;

import com.example.Example.Dtos.GMApprovalDto;
import com.example.Example.repository.IGMApprovalRepo;
import com.example.Example.services.IGMApproveService;
import com.example.Example.utilities.DataResult;
import com.example.Example.utilities.Result;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gm")
public class GMApprovalController {

    private final IGMApproveService gmApproveService;

    public GMApprovalController(IGMApproveService gmApproveService) {
        this.gmApproveService = gmApproveService;
    }

    @GetMapping("/findAll")
    public DataResult<List<GMApprovalDto>> findAll() {
        return gmApproveService.getAll();
    }

    @PutMapping("/approve/{id}")
    public Result approveDepartment(@PathVariable Long id, @RequestParam Long newDepartmentId) {
        return gmApproveService.approve(id, newDepartmentId);
    }

}
