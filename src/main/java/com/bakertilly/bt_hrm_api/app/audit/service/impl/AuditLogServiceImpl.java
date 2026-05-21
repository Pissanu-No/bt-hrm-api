package com.bakertilly.bt_hrm_api.app.audit.service.impl;

import com.bakertilly.bt_hrm_api.app.audit.dto.AuditLogEntry;
import com.bakertilly.bt_hrm_api.app.audit.dto.AuditLogResponse;
import com.bakertilly.bt_hrm_api.app.audit.entity.AuditLog;
import com.bakertilly.bt_hrm_api.app.audit.mapper.AuditLogMapper;
import com.bakertilly.bt_hrm_api.app.audit.repository.AuditLogRepository;
import com.bakertilly.bt_hrm_api.app.audit.service.AuditLogService;
import com.bakertilly.bt_hrm_api.app.common.exception.ResourceNotFoundException;
import com.bakertilly.bt_hrm_api.app.common.response.PagedResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuditLogServiceImpl implements AuditLogService {
    private final AuditLogRepository auditLogRepository;
    private final AuditLogMapper auditLogMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void record(AuditLogEntry entry) {
        AuditLog auditLog = new AuditLog();
        auditLog.setAuditLogId(UUID.randomUUID().toString());
        auditLog.setUserId(entry.getUserId());
        auditLog.setUsername(entry.getUsername());
        auditLog.setActionType(entry.getActionType());
        auditLog.setModuleCode(entry.getModuleCode());
        auditLog.setTableName(entry.getTableName());
        auditLog.setRecordId(entry.getRecordId());
        auditLog.setOldValue(entry.getOldValue());
        auditLog.setNewValue(entry.getNewValue());
        auditLog.setIpAddress(entry.getIpAddress());
        auditLog.setUserAgent(entry.getUserAgent());
        auditLog.setRequestId(entry.getRequestId());
        auditLog.setCreatedBy(entry.getUsername());
        auditLogRepository.save(auditLog);
    }

    @Override
    @Transactional(readOnly = true)
    public PagedResponse<AuditLogResponse> findAll(Pageable pageable) {
        return PagedResponse.from(auditLogRepository.findAll(pageable).map(auditLogMapper::toResponse));
    }

    @Override
    @Transactional(readOnly = true)
    public AuditLogResponse findById(String auditLogId) {
        return auditLogRepository.findById(auditLogId)
                .map(auditLogMapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Audit log not found"));
    }
}
