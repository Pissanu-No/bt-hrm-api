package com.bakertilly.bt_hrm_api.app.audit.controller;

import com.bakertilly.bt_hrm_api.app.audit.dto.AuditLogResponse;
import com.bakertilly.bt_hrm_api.app.audit.service.AuditLogService;
import com.bakertilly.bt_hrm_api.app.common.response.ApiResponse;
import com.bakertilly.bt_hrm_api.app.common.response.PagedResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/s/audit-logs")
@Tag(name = "Audit Log", description = "Audit log read APIs")
public class AuditLogController {
    private final AuditLogService auditLogService;

    @GetMapping
    @Operation(summary = "List audit logs")
    public ApiResponse<PagedResponse<AuditLogResponse>> findAll(
            @ParameterObject @PageableDefault(size = 10, sort = "createdAt") Pageable pageable) {
        return ApiResponse.success(auditLogService.findAll(pageable));
    }

    @GetMapping("/{auditLogId}")
    @Operation(summary = "Get audit log by ID")
    public ApiResponse<AuditLogResponse> findById(@PathVariable String auditLogId) {
        return ApiResponse.success(auditLogService.findById(auditLogId));
    }
}
