package com.bakertilly.bt_hrm_api.app.audit.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@Schema(description = "Audit log response")
public class AuditLogResponse {
    private String auditLogId;
    private String userId;
    private String username;
    private String actionType;
    private String moduleCode;
    private String tableName;
    private String recordId;
    private String oldValue;
    private String newValue;
    private String ipAddress;
    private String userAgent;
    private String requestId;
    private LocalDateTime createdAt;
    private String createdBy;
}
