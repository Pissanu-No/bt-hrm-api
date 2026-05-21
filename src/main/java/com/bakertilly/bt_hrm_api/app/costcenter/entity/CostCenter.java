package com.bakertilly.bt_hrm_api.app.costcenter.entity;

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
@Table(name = "hris_master_cost_center", schema = "hris")
public class CostCenter extends AuditableSoftDeleteEntity implements UuidPrimaryKeyEntity {
    @Id
    @Column(name = "cost_center_id", length = 60, nullable = false)
    private String costCenterId;
    @Column(name = "company_id", length = 60, nullable = false)
    private String companyId;
    @Column(name = "cost_center_code", length = 50, nullable = false)
    private String costCenterCode;
    @Column(name = "cost_center_name", nullable = false)
    private String costCenterName;
    @Column(name = "cost_center_name_local")
    private String costCenterNameLocal;
    @Column(name = "cost_center_type", length = 100)
    private String costCenterType;
    @Column(name = "sort_order", nullable = false)
    private Integer sortOrder = 0;
    @Column(name = "description", length = 1000)
    private String description;
    @Override public String getPrimaryKeyValue() { return costCenterId; }
    @Override public void setPrimaryKeyValue(String primaryKeyValue) { costCenterId = primaryKeyValue; }
    @Override public String getPrimaryKeyFieldName() { return "costCenterId"; }
}
