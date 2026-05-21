package com.bakertilly.bt_hrm_api.app.audit.repository;

import com.bakertilly.bt_hrm_api.app.audit.entity.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditLogRepository extends JpaRepository<AuditLog, String> {
}
