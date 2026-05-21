package com.bakertilly.bt_hrm_api.app.audit.mapper;

import com.bakertilly.bt_hrm_api.app.audit.dto.AuditLogResponse;
import com.bakertilly.bt_hrm_api.app.audit.entity.AuditLog;
import org.springframework.stereotype.Component;

@Component
public class AuditLogMapper {
    public AuditLogResponse toResponse(AuditLog entity) {
        return AuditLogResponse.builder()
                .auditLogId(entity.getAuditLogId())
                .userId(entity.getUserId())
                .username(entity.getUsername())
                .actionType(entity.getActionType())
                .moduleCode(entity.getModuleCode())
                .tableName(entity.getTableName())
                .recordId(entity.getRecordId())
                .oldValue(entity.getOldValue())
                .newValue(entity.getNewValue())
                .ipAddress(entity.getIpAddress())
                .userAgent(entity.getUserAgent())
                .requestId(entity.getRequestId())
                .createdAt(entity.getCreatedAt())
                .createdBy(entity.getCreatedBy())
                .build();
    }
}
