package com.bakertilly.bt_hrm_api.app.department.controller;

import com.bakertilly.bt_hrm_api.app.common.response.ApiResponse;
import com.bakertilly.bt_hrm_api.app.common.response.PagedResponse;
import com.bakertilly.bt_hrm_api.app.department.dto.DepartmentRequest;
import com.bakertilly.bt_hrm_api.app.department.dto.DepartmentResponse;
import com.bakertilly.bt_hrm_api.app.department.service.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/s/departments")
@Tag(name = "department", description = "Department master data APIs")
public class DepartmentController {
    private final DepartmentService departmentService;

    @GetMapping
    @Operation(summary = "List departments")
    @io.swagger.v3.oas.annotations.responses.ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Departments retrieved")
    })
    public ApiResponse<PagedResponse<DepartmentResponse>> findAll(
            @ParameterObject @PageableDefault(size = 10, sort = "createdAt") Pageable pageable) {
        return ApiResponse.success(departmentService.findAll(pageable));
    }

    @GetMapping("/{departmentId}")
    @Operation(summary = "Get department by ID")
    @io.swagger.v3.oas.annotations.responses.ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Department retrieved"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Department not found")
    })
    public ApiResponse<DepartmentResponse> findById(
            @Parameter(description = "Department ID") @PathVariable String departmentId) {
        return ApiResponse.success(departmentService.findById(departmentId));
    }

    @PostMapping
    @Operation(summary = "Create department")
    @io.swagger.v3.oas.annotations.responses.ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Department created"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Validation failed"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "409", description = "Department code already exists")
    })
    public ResponseEntity<ApiResponse<DepartmentResponse>> create(@Valid @RequestBody DepartmentRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(departmentService.create(request)));
    }

    @PutMapping("/{departmentId}")
    @Operation(summary = "Update department")
    public ApiResponse<DepartmentResponse> update(
            @Parameter(description = "Department ID") @PathVariable String departmentId,
            @Valid @RequestBody DepartmentRequest request) {
        return ApiResponse.success(departmentService.update(departmentId, request));
    }

    @DeleteMapping("/{departmentId}")
    @Operation(summary = "Soft delete department")
    public ApiResponse<Void> delete(@Parameter(description = "Department ID") @PathVariable String departmentId) {
        departmentService.delete(departmentId);
        return ApiResponse.success(null);
    }
}
