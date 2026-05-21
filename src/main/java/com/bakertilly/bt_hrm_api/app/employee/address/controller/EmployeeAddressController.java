package com.bakertilly.bt_hrm_api.app.employee.address.controller;

import com.bakertilly.bt_hrm_api.app.common.response.ApiResponse;
import com.bakertilly.bt_hrm_api.app.common.response.PagedResponse;
import com.bakertilly.bt_hrm_api.app.employee.address.dto.EmployeeAddressCreateRequest;
import com.bakertilly.bt_hrm_api.app.employee.address.dto.EmployeeAddressResponse;
import com.bakertilly.bt_hrm_api.app.employee.address.dto.EmployeeAddressUpdateRequest;
import com.bakertilly.bt_hrm_api.app.employee.address.service.EmployeeAddressService;
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
@RequestMapping("/v1/s/employees/{employeeId}/addresses")
@Tag(name = "Employee Address", description = "Employee address APIs")
public class EmployeeAddressController {
    private final EmployeeAddressService service;

    @GetMapping
    @Operation(summary = "List employee addresses")
    public ApiResponse<PagedResponse<EmployeeAddressResponse>> findAll(
            @PathVariable String employeeId,
            @ParameterObject @PageableDefault(size = 10, sort = "createdAt") Pageable pageable) {
        return ApiResponse.success(service.findAll(employeeId, pageable));
    }

    @GetMapping("/{addressId}")
    @Operation(summary = "Get employee address by ID")
    public ApiResponse<EmployeeAddressResponse> findById(@PathVariable String employeeId,
                                                         @PathVariable String addressId) {
        return ApiResponse.success(service.findById(employeeId, addressId));
    }

    @PostMapping
    @Operation(summary = "Create employee address")
    public ResponseEntity<ApiResponse<EmployeeAddressResponse>> create(
            @PathVariable String employeeId,
            @Valid @RequestBody EmployeeAddressCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(service.create(employeeId, request)));
    }

    @PutMapping("/{addressId}")
    @Operation(summary = "Update employee address")
    public ApiResponse<EmployeeAddressResponse> update(@PathVariable String employeeId,
                                                       @PathVariable String addressId,
                                                       @Valid @RequestBody EmployeeAddressUpdateRequest request) {
        return ApiResponse.success(service.update(employeeId, addressId, request));
    }

    @PutMapping("/{addressId}/set-registered")
    @Operation(summary = "Set employee registered address")
    public ApiResponse<EmployeeAddressResponse> setRegistered(@PathVariable String employeeId,
                                                              @PathVariable String addressId) {
        return ApiResponse.success(service.setRegistered(employeeId, addressId));
    }

    @PutMapping("/{addressId}/set-contact")
    @Operation(summary = "Set employee contact address")
    public ApiResponse<EmployeeAddressResponse> setContact(@PathVariable String employeeId,
                                                           @PathVariable String addressId) {
        return ApiResponse.success(service.setContact(employeeId, addressId));
    }

    @DeleteMapping("/{addressId}")
    @Operation(summary = "Soft delete employee address")
    public ApiResponse<Void> delete(@PathVariable String employeeId, @PathVariable String addressId) {
        service.delete(employeeId, addressId);
        return ApiResponse.success(null);
    }
}
