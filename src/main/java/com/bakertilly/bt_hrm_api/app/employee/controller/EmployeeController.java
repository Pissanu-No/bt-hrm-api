package com.bakertilly.bt_hrm_api.app.employee.controller;

import com.bakertilly.bt_hrm_api.app.common.response.ApiResponse;
import com.bakertilly.bt_hrm_api.app.common.response.PagedResponse;
import com.bakertilly.bt_hrm_api.app.employee.dto.EmployeeListResponse;
import com.bakertilly.bt_hrm_api.app.employee.dto.EmployeeRequest;
import com.bakertilly.bt_hrm_api.app.employee.dto.EmployeeResponse;
import com.bakertilly.bt_hrm_api.app.employee.service.EmployeeService;
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
@RequestMapping("/v1/s/employees")
@Tag(name = "employee", description = "Employee basic management APIs")
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping
    @Operation(summary = "List employees")
    @io.swagger.v3.oas.annotations.responses.ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Employees retrieved")
    })
    public ApiResponse<PagedResponse<EmployeeListResponse>> findAll(
            @ParameterObject @PageableDefault(size = 10, sort = "createdAt") Pageable pageable) {
        return ApiResponse.success(employeeService.findAll(pageable));
    }

    @GetMapping("/{employeeId}")
    @Operation(summary = "Get employee by ID")
    @io.swagger.v3.oas.annotations.responses.ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Employee retrieved"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Employee not found")
    })
    public ApiResponse<EmployeeResponse> findById(
            @Parameter(description = "Employee ID") @PathVariable String employeeId) {
        return ApiResponse.success(employeeService.findById(employeeId));
    }

    @PostMapping
    @Operation(summary = "Create employee")
    @io.swagger.v3.oas.annotations.responses.ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Employee created"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Validation failed"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "409", description = "Employee code already exists")
    })
    public ResponseEntity<ApiResponse<EmployeeResponse>> create(@Valid @RequestBody EmployeeRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(employeeService.create(request)));
    }

    @PutMapping("/{employeeId}")
    @Operation(summary = "Update employee")
    public ApiResponse<EmployeeResponse> update(
            @Parameter(description = "Employee ID") @PathVariable String employeeId,
            @Valid @RequestBody EmployeeRequest request) {
        return ApiResponse.success(employeeService.update(employeeId, request));
    }

    @DeleteMapping("/{employeeId}")
    @Operation(summary = "Soft delete employee")
    public ApiResponse<Void> delete(@Parameter(description = "Employee ID") @PathVariable String employeeId) {
        employeeService.delete(employeeId);
        return ApiResponse.success(null);
    }
}
