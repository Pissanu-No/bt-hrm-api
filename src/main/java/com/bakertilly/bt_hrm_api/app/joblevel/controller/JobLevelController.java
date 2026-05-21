package com.bakertilly.bt_hrm_api.app.joblevel.controller;

import com.bakertilly.bt_hrm_api.app.common.response.ApiResponse;
import com.bakertilly.bt_hrm_api.app.common.response.PagedResponse;
import com.bakertilly.bt_hrm_api.app.joblevel.dto.JobLevelRequest;
import com.bakertilly.bt_hrm_api.app.joblevel.dto.JobLevelResponse;
import com.bakertilly.bt_hrm_api.app.joblevel.service.JobLevelService;
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
@RequestMapping("/v1/s/master/job-levels")
@Tag(name = "Job Level", description = "Job level master data APIs")
public class JobLevelController {
    private final JobLevelService service;
    @GetMapping @Operation(summary = "List job levels")
    public ApiResponse<PagedResponse<JobLevelResponse>> findAll(@ParameterObject @PageableDefault(size = 10, sort = "createdAt") Pageable pageable) { return ApiResponse.success(service.findAll(pageable)); }
    @GetMapping("/{jobLevelId}") @Operation(summary = "Get job level by ID")
    public ApiResponse<JobLevelResponse> findById(@PathVariable String jobLevelId) { return ApiResponse.success(service.findById(jobLevelId)); }
    @PostMapping @Operation(summary = "Create job level")
    public ResponseEntity<ApiResponse<JobLevelResponse>> create(@Valid @RequestBody JobLevelRequest request) { return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(service.create(request))); }
    @PutMapping("/{jobLevelId}") @Operation(summary = "Update job level")
    public ApiResponse<JobLevelResponse> update(@PathVariable String jobLevelId, @Valid @RequestBody JobLevelRequest request) { return ApiResponse.success(service.update(jobLevelId, request)); }
    @DeleteMapping("/{jobLevelId}") @Operation(summary = "Soft delete job level")
    public ApiResponse<Void> delete(@PathVariable String jobLevelId) { service.delete(jobLevelId); return ApiResponse.success(null); }
}
