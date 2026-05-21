package com.bakertilly.bt_hrm_api.app.branch.controller;

import com.bakertilly.bt_hrm_api.app.branch.dto.BranchRequest;
import com.bakertilly.bt_hrm_api.app.branch.dto.BranchResponse;
import com.bakertilly.bt_hrm_api.app.branch.service.BranchService;
import com.bakertilly.bt_hrm_api.app.common.response.ApiResponse;
import com.bakertilly.bt_hrm_api.app.common.response.PagedResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/s/master/branches")
@Tag(name = "branch", description = "Branch master data APIs")
public class BranchController {
    private final BranchService service;

    @GetMapping
    @Operation(summary = "List branches")
    public ApiResponse<PagedResponse<BranchResponse>> findAll(@ParameterObject @PageableDefault(size = 10, sort = "createdAt") Pageable pageable) {
        return ApiResponse.success(service.findAll(pageable));
    }

    @GetMapping("/{branchId}")
    @Operation(summary = "Get branch by ID")
    public ApiResponse<BranchResponse> findById(@PathVariable String branchId) {
        return ApiResponse.success(service.findById(branchId));
    }

    @PostMapping
    @Operation(summary = "Create branch")
    public ResponseEntity<ApiResponse<BranchResponse>> create(@Valid @RequestBody BranchRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(service.create(request)));
    }

    @PutMapping("/{branchId}")
    @Operation(summary = "Update branch")
    public ApiResponse<BranchResponse> update(@PathVariable String branchId, @Valid @RequestBody BranchRequest request) {
        return ApiResponse.success(service.update(branchId, request));
    }

    @DeleteMapping("/{branchId}")
    @Operation(summary = "Soft delete branch")
    public ApiResponse<Void> delete(@PathVariable String branchId) {
        service.delete(branchId);
        return ApiResponse.success(null);
    }
}
