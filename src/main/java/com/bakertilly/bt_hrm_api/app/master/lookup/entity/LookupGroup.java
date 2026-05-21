package com.bakertilly.bt_hrm_api.app.master.lookup.entity;

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
@Table(name = "hris_master_lookup_group", schema = "hris")
public class LookupGroup extends AuditableSoftDeleteEntity implements UuidPrimaryKeyEntity {
    @Id
    @Column(name = "lookup_group_id", length = 60, nullable = false)
    private String lookupGroupId;
    @Column(name = "lookup_group_code", length = 100, nullable = false)
    private String lookupGroupCode;
    @Column(name = "lookup_group_name", nullable = false)
    private String lookupGroupName;
    @Column(name = "lookup_group_name_local")
    private String lookupGroupNameLocal;
    @Column(name = "module_code", length = 100, nullable = false)
    private String moduleCode;
    @Column(name = "description", length = 1000)
    private String description;
    @Column(name = "is_system", nullable = false)
    private Boolean isSystem = false;
    @Column(name = "sort_order", nullable = false)
    private Integer sortOrder = 0;
    @Override public String getPrimaryKeyValue() { return lookupGroupId; }
    @Override public void setPrimaryKeyValue(String primaryKeyValue) { lookupGroupId = primaryKeyValue; }
    @Override public String getPrimaryKeyFieldName() { return "lookupGroupId"; }
}
