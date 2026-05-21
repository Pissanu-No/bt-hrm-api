package com.bakertilly.bt_hrm_api.app.employee.certification.controller;

import com.bakertilly.bt_hrm_api.app.common.response.ApiResponse;
import com.bakertilly.bt_hrm_api.app.common.response.PagedResponse;
import com.bakertilly.bt_hrm_api.app.employee.certification.dto.CertificationRequest;
import com.bakertilly.bt_hrm_api.app.employee.certification.dto.CertificationResponse;
import com.bakertilly.bt_hrm_api.app.employee.certification.service.CertificationService;
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
@RequestMapping("/v1/s/employees/{employeeId}/certifications")
@Tag(name = "Employee Certification", description = "Employee certification APIs")
public class CertificationController {
    private final CertificationService service;

    @GetMapping
    @Operation(summary = "List employee certifications")
    public ApiResponse<PagedResponse<CertificationResponse>> findAll(
            @PathVariable String employeeId,
            @ParameterObject @PageableDefault(size = 10, sort = "createdAt") Pageable pageable) {
        return ApiResponse.success(service.findAll(employeeId, pageable));
    }

    @GetMapping("/{certificationId}")
    @Operation(summary = "Get employee certification by ID")
    public ApiResponse<CertificationResponse> findById(@PathVariable String employeeId,
                                                       @PathVariable String certificationId) {
        return ApiResponse.success(service.findById(employeeId, certificationId));
    }

    @PostMapping
    @Operation(summary = "Create employee certification")
    public ResponseEntity<ApiResponse<CertificationResponse>> create(
            @PathVariable String employeeId,
            @Valid @RequestBody CertificationRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(service.create(employeeId, request)));
    }

    @PutMapping("/{certificationId}")
    @Operation(summary = "Update employee certification")
    public ApiResponse<CertificationResponse> update(@PathVariable String employeeId,
                                                     @PathVariable String certificationId,
                                                     @Valid @RequestBody CertificationRequest request) {
        return ApiResponse.success(service.update(employeeId, certificationId, request));
    }

    @DeleteMapping("/{certificationId}")
    @Operation(summary = "Soft delete employee certification")
    public ApiResponse<Void> delete(@PathVariable String employeeId, @PathVariable String certificationId) {
        service.delete(employeeId, certificationId);
        return ApiResponse.success(null);
    }
}
