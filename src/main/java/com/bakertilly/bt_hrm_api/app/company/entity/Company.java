package com.bakertilly.bt_hrm_api.app.company.entity;

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
@Table(name = "hris_master_company", schema = "hris")
public class Company extends AuditableSoftDeleteEntity implements UuidPrimaryKeyEntity {
    @Id
    @Column(name = "company_id", length = 60, nullable = false)
    private String companyId;

    @Column(name = "company_code", length = 50, nullable = false)
    private String companyCode;

    @Column(name = "company_name", nullable = false)
    private String companyName;

    @Column(name = "company_name_local")
    private String companyNameLocal;

    @Column(name = "company_short_name", length = 100)
    private String companyShortName;

    @Column(name = "company_logo_url", length = 1000)
    private String companyLogoUrl;

    @Column(name = "tax_id", length = 50)
    private String taxId;

    @Column(name = "registration_no", length = 100)
    private String registrationNo;

    @Column(name = "email")
    private String email;

    @Column(name = "phone", length = 50)
    private String phone;

    @Column(name = "website")
    private String website;

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

    @Override
    public String getPrimaryKeyValue() {
        return companyId;
    }

    @Override
    public void setPrimaryKeyValue(String primaryKeyValue) {
        companyId = primaryKeyValue;
    }

    @Override
    public String getPrimaryKeyFieldName() {
        return "companyId";
    }
}
