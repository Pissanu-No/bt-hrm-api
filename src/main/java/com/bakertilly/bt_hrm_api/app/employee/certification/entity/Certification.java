package com.bakertilly.bt_hrm_api.app.employee.certification.entity;

import com.bakertilly.bt_hrm_api.app.common.persistence.AuditableSoftDeleteEntity;
import com.bakertilly.bt_hrm_api.app.common.persistence.UuidPrimaryKeyEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "hris_transaction_certification", schema = "hris")
public class Certification extends AuditableSoftDeleteEntity implements UuidPrimaryKeyEntity {
    @Id
    @Column(name = "certification_id", length = 60, nullable = false)
    private String certificationId;

    @Column(name = "employee_id", length = 60, nullable = false)
    private String employeeId;

    @Column(name = "certification_name", nullable = false)
    private String certificationName;

    @Column(name = "certification_code", length = 100)
    private String certificationCode;

    @Column(name = "issuing_organization")
    private String issuingOrganization;

    @Column(name = "credential_id")
    private String credentialId;

    @Column(name = "credential_url", length = 1000)
    private String credentialUrl;

    @Column(name = "issued_date")
    private LocalDate issuedDate;

    @Column(name = "expired_date")
    private LocalDate expiredDate;

    @Column(name = "never_expires", nullable = false)
    private Boolean neverExpires = false;

    @Column(name = "certification_status", length = 50, nullable = false)
    private String certificationStatus = "ACTIVE";

    @Column(name = "description", length = 1000)
    private String description;

    @Override
    public String getPrimaryKeyValue() {
        return certificationId;
    }

    @Override
    public void setPrimaryKeyValue(String primaryKeyValue) {
        certificationId = primaryKeyValue;
    }

    @Override
    public String getPrimaryKeyFieldName() {
        return "certificationId";
    }
}
