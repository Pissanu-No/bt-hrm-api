package com.bakertilly.bt_hrm_api.app.master.lookup.entity;

import com.bakertilly.bt_hrm_api.app.common.persistence.AuditableSoftDeleteEntity;
import com.bakertilly.bt_hrm_api.app.common.persistence.UuidPrimaryKeyEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "hris_master_lookup_value", schema = "hris")
public class LookupValue extends AuditableSoftDeleteEntity implements UuidPrimaryKeyEntity {
    @Id
    @Column(name = "lookup_value_id", length = 60, nullable = false)
    private String lookupValueId;
    @Column(name = "lookup_group_id", length = 60, nullable = false)
    private String lookupGroupId;
    @Column(name = "lookup_code", length = 100, nullable = false)
    private String lookupCode;
    @Column(name = "lookup_name", nullable = false)
    private String lookupName;
    @Column(name = "lookup_name_local")
    private String lookupNameLocal;
    @Column(name = "value_text")
    private String valueText;
    @Column(name = "value_number", precision = 18, scale = 4)
    private BigDecimal valueNumber;
    @Column(name = "color_code", length = 50)
    private String colorCode;
    @Column(name = "icon_name", length = 100)
    private String iconName;
    @Column(name = "description", length = 1000)
    private String description;
    @Column(name = "is_default", nullable = false)
    private Boolean isDefault = false;
    @Column(name = "is_system", nullable = false)
    private Boolean isSystem = false;
    @Column(name = "sort_order", nullable = false)
    private Integer sortOrder = 0;
    @Override public String getPrimaryKeyValue() { return lookupValueId; }
    @Override public void setPrimaryKeyValue(String primaryKeyValue) { lookupValueId = primaryKeyValue; }
    @Override public String getPrimaryKeyFieldName() { return "lookupValueId"; }
}
