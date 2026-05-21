package com.bakertilly.bt_hrm_api.app.audit.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuditLogEntry {
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
}
