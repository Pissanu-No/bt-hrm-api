package com.bakertilly.bt_hrm_api.app.position.entity;

import com.bakertilly.bt_hrm_api.app.common.persistence.AuditableSoftDeleteEntity;
import com.bakertilly.bt_hrm_api.app.common.persistence.UuidPrimaryKeyEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "hris_master_position", schema = "hris")
public class Position extends AuditableSoftDeleteEntity implements UuidPrimaryKeyEntity {
    @Id
    @Column(name = "position_id", length = 60, nullable = false)
    private String positionId;

    @Column(name = "company_id", length = 60, nullable = false)
    private String companyId;

    @Column(name = "department_id", length = 60)
    private String departmentId;

    @Column(name = "job_level_id", length = 60)
    private String jobLevelId;

    @Column(name = "job_family_id", length = 60)
    private String jobFamilyId;

    @Column(name = "position_code", length = 50, nullable = false)
    private String positionCode;

    @Column(name = "position_name", nullable = false)
    private String positionName;

    @Column(name = "position_name_local")
    private String positionNameLocal;

    @Column(name = "position_type", length = 100)
    private String positionType;

    @Column(name = "description", length = 1000)
    private String description;

    @Column(name = "sort_order", nullable = false)
    private Integer sortOrder = 0;

    @Column(name = "is_manager_position", nullable = false)
    private Boolean isManagerPosition = false;

    @Override
    public String getPrimaryKeyValue() {
        return positionId;
    }

    @Override
    public void setPrimaryKeyValue(String primaryKeyValue) {
        positionId = primaryKeyValue;
    }

    @Override
    public String getPrimaryKeyFieldName() {
        return "positionId";
    }
}
