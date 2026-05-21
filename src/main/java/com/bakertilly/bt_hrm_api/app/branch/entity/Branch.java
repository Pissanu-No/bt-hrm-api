package com.bakertilly.bt_hrm_api.app.branch.entity;

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
@Table(name = "hris_master_branch", schema = "hris")
public class Branch extends AuditableSoftDeleteEntity implements UuidPrimaryKeyEntity {
    @Id
    @Column(name = "branch_id", length = 60, nullable = false)
    private String branchId;

    @Column(name = "company_id", length = 60, nullable = false)
    private String companyId;

    @Column(name = "branch_code", length = 50, nullable = false)
    private String branchCode;

    @Column(name = "branch_name", nullable = false)
    private String branchName;

    @Column(name = "branch_name_local")
    private String branchNameLocal;

    @Column(name = "branch_type", length = 50)
    private String branchType;

    @Column(name = "email")
    private String email;

    @Column(name = "phone", length = 50)
    private String phone;

    @Column(name = "address_line1", length = 500)
    private String addressLine1;

    @Column(name = "address_line2", length = 500)
    private String addressLine2;

    @Column(name = "province", length = 100)
    private String province;

    @Column(name = "country", length = 100)
    private String country;

    @Column(name = "postal_code", length = 20)
    private String postalCode;

    @Column(name = "sort_order", nullable = false)
    private Integer sortOrder = 0;

    @Column(name = "description", length = 1000)
    private String description;

    @Override
    public String getPrimaryKeyValue() {
        return branchId;
    }

    @Override
    public void setPrimaryKeyValue(String primaryKeyValue) {
        branchId = primaryKeyValue;
    }

    @Override
    public String getPrimaryKeyFieldName() {
        return "branchId";
    }
}
