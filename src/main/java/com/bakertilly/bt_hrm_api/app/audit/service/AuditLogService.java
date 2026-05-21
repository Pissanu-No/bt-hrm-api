package com.bakertilly.bt_hrm_api.app.audit.service;

import com.bakertilly.bt_hrm_api.app.audit.dto.AuditLogEntry;
import com.bakertilly.bt_hrm_api.app.audit.dto.AuditLogResponse;
import com.bakertilly.bt_hrm_api.app.common.response.PagedResponse;
import org.springframework.data.domain.Pageable;

public interface AuditLogService {
    void record(AuditLogEntry entry);

    PagedResponse<AuditLogResponse> findAll(Pageable pageable);

    AuditLogResponse findById(String auditLogId);
}
