package com.bakertilly.bt_hrm_api.app.department.entity;

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
@Table(name = "hris_master_department", schema = "hris")
public class Department extends AuditableSoftDeleteEntity implements UuidPrimaryKeyEntity {
    @Id
    @Column(name = "department_id", length = 60, nullable = false)
    private String departmentId;

    @Column(name = "company_id", length = 60, nullable = false)
    private String companyId;

    @Column(name = "branch_id", length = 60)
    private String branchId;

    @Column(name = "cost_center_id", length = 60)
    private String costCenterId;

    @Column(name = "parent_department_id", length = 60)
    private String parentDepartmentId;

    @Column(name = "department_code", length = 50, nullable = false)
    private String departmentCode;

    @Column(name = "department_name", nullable = false)
    private String departmentName;

    @Column(name = "department_name_local")
    private String departmentNameLocal;

    @Column(name = "department_type", length = 100)
    private String departmentType;

    @Column(name = "description", length = 1000)
    private String description;

    @Column(name = "sort_order", nullable = false)
    private Integer sortOrder = 0;

    @Override
    public String getPrimaryKeyValue() {
        return departmentId;
    }

    @Override
    public void setPrimaryKeyValue(String primaryKeyValue) {
        departmentId = primaryKeyValue;
    }

    @Override
    public String getPrimaryKeyFieldName() {
        return "departmentId";
    }
}
