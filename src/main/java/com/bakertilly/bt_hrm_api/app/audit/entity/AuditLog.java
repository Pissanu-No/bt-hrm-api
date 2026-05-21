package com.bakertilly.bt_hrm_api.app.audit.entity;

import com.bakertilly.bt_hrm_api.app.common.persistence.UuidPrimaryKeyEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "hris_transaction_audit_log", schema = "hris")
public class AuditLog implements UuidPrimaryKeyEntity {
    @Id
    @Column(name = "audit_log_id", length = 60, nullable = false)
    private String auditLogId;

    @Column(name = "user_id", length = 60)
    private String userId;

    @Column(name = "username", length = 100)
    private String username;

    @Column(name = "action_type", length = 50, nullable = false)
    private String actionType;

    @Column(name = "module_code", length = 100)
    private String moduleCode;

    @Column(name = "table_name", length = 150)
    private String tableName;

    @Column(name = "record_id", length = 60)
    private String recordId;

    @JdbcTypeCode(SqlTypes.LONGNVARCHAR)
    @Column(name = "old_value", columnDefinition = "nvarchar(max)")
    private String oldValue;

    @JdbcTypeCode(SqlTypes.LONGNVARCHAR)
    @Column(name = "new_value", columnDefinition = "nvarchar(max)")
    private String newValue;

    @Column(name = "ip_address", length = 100)
    private String ipAddress;

    @Column(name = "user_agent", length = 1000)
    private String userAgent;

    @Column(name = "request_id", length = 100)
    private String requestId;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "created_by", length = 100)
    private String createdBy;

    @PrePersist
    protected void onCreate() {
        ensureUuidPrimaryKey();
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }

    @Override
    public String getPrimaryKeyValue() {
        return auditLogId;
    }

    @Override
    public void setPrimaryKeyValue(String primaryKeyValue) {
        auditLogId = primaryKeyValue;
    }

    @Override
    public String getPrimaryKeyFieldName() {
        return "auditLogId";
    }
}
