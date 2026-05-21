package com.bakertilly.bt_hrm_api.app.employee.education.controller;

import com.bakertilly.bt_hrm_api.app.common.response.ApiResponse;
import com.bakertilly.bt_hrm_api.app.common.response.PagedResponse;
import com.bakertilly.bt_hrm_api.app.employee.education.dto.EducationRequest;
import com.bakertilly.bt_hrm_api.app.employee.education.dto.EducationResponse;
import com.bakertilly.bt_hrm_api.app.employee.education.service.EducationService;
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
@RequestMapping("/v1/s/employees/{employeeId}/educations")
@Tag(name = "Employee Education", description = "Employee education APIs")
public class EducationController {
    private final EducationService service;

    @GetMapping
    @Operation(summary = "List employee educations")
    public ApiResponse<PagedResponse<EducationResponse>> findAll(
            @PathVariable String employeeId,
            @ParameterObject @PageableDefault(size = 10, sort = "createdAt") Pageable pageable) {
        return ApiResponse.success(service.findAll(employeeId, pageable));
    }

    @GetMapping("/{educationId}")
    @Operation(summary = "Get employee education by ID")
    public ApiResponse<EducationResponse> findById(@PathVariable String employeeId, @PathVariable String educationId) {
        return ApiResponse.success(service.findById(employeeId, educationId));
    }

    @PostMapping
    @Operation(summary = "Create employee education")
    public ResponseEntity<ApiResponse<EducationResponse>> create(
            @PathVariable String employeeId,
            @Valid @RequestBody EducationRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(service.create(employeeId, request)));
    }

    @PutMapping("/{educationId}")
    @Operation(summary = "Update employee education")
    public ApiResponse<EducationResponse> update(@PathVariable String employeeId,
                                                 @PathVariable String educationId,
                                                 @Valid @RequestBody EducationRequest request) {
        return ApiResponse.success(service.update(employeeId, educationId, request));
    }

    @PutMapping("/{educationId}/set-highest")
    @Operation(summary = "Set highest education")
    public ApiResponse<EducationResponse> setHighest(@PathVariable String employeeId,
                                                     @PathVariable String educationId) {
        return ApiResponse.success(service.setHighest(employeeId, educationId));
    }

    @DeleteMapping("/{educationId}")
    @Operation(summary = "Soft delete employee education")
    public ApiResponse<Void> delete(@PathVariable String employeeId, @PathVariable String educationId) {
        service.delete(employeeId, educationId);
        return ApiResponse.success(null);
    }
}
