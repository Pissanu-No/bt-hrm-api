package com.bakertilly.bt_hrm_api.app.costcenter.controller;

import com.bakertilly.bt_hrm_api.app.common.response.ApiResponse;
import com.bakertilly.bt_hrm_api.app.common.response.PagedResponse;
import com.bakertilly.bt_hrm_api.app.costcenter.dto.CostCenterRequest;
import com.bakertilly.bt_hrm_api.app.costcenter.dto.CostCenterResponse;
import com.bakertilly.bt_hrm_api.app.costcenter.service.CostCenterService;
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
@RequestMapping("/v1/s/master/cost-centers")
@Tag(name = "Cost Center", description = "Cost center master data APIs")
public class CostCenterController {
    private final CostCenterService service;
    @GetMapping @Operation(summary = "List cost centers")
    public ApiResponse<PagedResponse<CostCenterResponse>> findAll(@ParameterObject @PageableDefault(size = 10, sort = "createdAt") Pageable pageable) { return ApiResponse.success(service.findAll(pageable)); }
    @GetMapping("/{costCenterId}") @Operation(summary = "Get cost center by ID")
    public ApiResponse<CostCenterResponse> findById(@PathVariable String costCenterId) { return ApiResponse.success(service.findById(costCenterId)); }
    @PostMapping @Operation(summary = "Create cost center")
    public ResponseEntity<ApiResponse<CostCenterResponse>> create(@Valid @RequestBody CostCenterRequest request) { return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(service.create(request))); }
    @PutMapping("/{costCenterId}") @Operation(summary = "Update cost center")
    public ApiResponse<CostCenterResponse> update(@PathVariable String costCenterId, @Valid @RequestBody CostCenterRequest request) { return ApiResponse.success(service.update(costCenterId, request)); }
    @DeleteMapping("/{costCenterId}") @Operation(summary = "Soft delete cost center")
    public ApiResponse<Void> delete(@PathVariable String costCenterId) { service.delete(costCenterId); return ApiResponse.success(null); }
}
