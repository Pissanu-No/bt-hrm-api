package com.bakertilly.bt_hrm_api.app.employee.emergencycontact.controller;

import com.bakertilly.bt_hrm_api.app.common.response.ApiResponse;
import com.bakertilly.bt_hrm_api.app.common.response.PagedResponse;
import com.bakertilly.bt_hrm_api.app.employee.emergencycontact.dto.EmergencyContactRequest;
import com.bakertilly.bt_hrm_api.app.employee.emergencycontact.dto.EmergencyContactResponse;
import com.bakertilly.bt_hrm_api.app.employee.emergencycontact.service.EmergencyContactService;
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
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/s/employees/{employeeId}/emergency-contacts")
@Tag(name = "Employee Emergency Contact", description = "Employee emergency contact APIs")
public class EmergencyContactController {
    private final EmergencyContactService service;

    @GetMapping
    @Operation(summary = "List employee emergency contacts")
    public ApiResponse<PagedResponse<EmergencyContactResponse>> findAll(
            @Parameter(description = "Employee ID") @PathVariable String employeeId,
            @ParameterObject @PageableDefault(size = 10, sort = "createdAt") Pageable pageable) {
        return ApiResponse.success(service.findAll(employeeId, pageable));
    }

    @GetMapping("/{emergencyContactId}")
    @Operation(summary = "Get employee emergency contact by ID")
    public ApiResponse<EmergencyContactResponse> findById(@PathVariable String employeeId,
                                                          @PathVariable String emergencyContactId) {
        return ApiResponse.success(service.findById(employeeId, emergencyContactId));
    }

    @PostMapping
    @Operation(summary = "Create employee emergency contact")
    public ResponseEntity<ApiResponse<EmergencyContactResponse>> create(
            @PathVariable String employeeId,
            @Valid @RequestBody EmergencyContactRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(service.create(employeeId, request)));
    }

    @PutMapping("/{emergencyContactId}")
    @Operation(summary = "Update employee emergency contact")
    public ApiResponse<EmergencyContactResponse> update(@PathVariable String employeeId,
                                                        @PathVariable String emergencyContactId,
                                                        @Valid @RequestBody EmergencyContactRequest request) {
        return ApiResponse.success(service.update(employeeId, emergencyContactId, request));
    }

    @PutMapping("/{emergencyContactId}/set-primary")
    @Operation(summary = "Set primary emergency contact")
    public ApiResponse<EmergencyContactResponse> setPrimary(@PathVariable String employeeId,
                                                            @PathVariable String emergencyContactId) {
        return ApiResponse.success(service.setPrimary(employeeId, emergencyContactId));
    }

    @DeleteMapping("/{emergencyContactId}")
    @Operation(summary = "Soft delete employee emergency contact")
    public ApiResponse<Void> delete(@PathVariable String employeeId, @PathVariable String emergencyContactId) {
        service.delete(employeeId, emergencyContactId);
        return ApiResponse.success(null);
    }
}
