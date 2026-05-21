package com.bakertilly.bt_hrm_api.app.location.controller;

import com.bakertilly.bt_hrm_api.app.common.response.ApiResponse;
import com.bakertilly.bt_hrm_api.app.common.response.PagedResponse;
import com.bakertilly.bt_hrm_api.app.location.dto.LocationRequest;
import com.bakertilly.bt_hrm_api.app.location.dto.LocationResponse;
import com.bakertilly.bt_hrm_api.app.location.service.LocationService;
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
@RequestMapping("/v1/s/master/locations")
@Tag(name = "location", description = "Location master data APIs")
public class LocationController {
    private final LocationService service;
    @GetMapping @Operation(summary = "List locations")
    public ApiResponse<PagedResponse<LocationResponse>> findAll(@ParameterObject @PageableDefault(size = 10, sort = "createdAt") Pageable pageable) { return ApiResponse.success(service.findAll(pageable)); }
    @GetMapping("/{locationId}") @Operation(summary = "Get location by ID")
    public ApiResponse<LocationResponse> findById(@PathVariable String locationId) { return ApiResponse.success(service.findById(locationId)); }
    @PostMapping @Operation(summary = "Create location")
    public ResponseEntity<ApiResponse<LocationResponse>> create(@Valid @RequestBody LocationRequest request) { return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(service.create(request))); }
    @PutMapping("/{locationId}") @Operation(summary = "Update location")
    public ApiResponse<LocationResponse> update(@PathVariable String locationId, @Valid @RequestBody LocationRequest request) { return ApiResponse.success(service.update(locationId, request)); }
    @DeleteMapping("/{locationId}") @Operation(summary = "Soft delete location")
    public ApiResponse<Void> delete(@PathVariable String locationId) { service.delete(locationId); return ApiResponse.success(null); }
}
