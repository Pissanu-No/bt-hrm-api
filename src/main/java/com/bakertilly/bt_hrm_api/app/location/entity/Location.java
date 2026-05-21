package com.bakertilly.bt_hrm_api.app.location.entity;

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
@Table(name = "hris_master_location", schema = "hris")
public class Location extends AuditableSoftDeleteEntity implements UuidPrimaryKeyEntity {
    @Id
    @Column(name = "location_id", length = 60, nullable = false)
    private String locationId;
    @Column(name = "company_id", length = 60, nullable = false)
    private String companyId;
    @Column(name = "branch_id", length = 60)
    private String branchId;
    @Column(name = "location_code", length = 50, nullable = false)
    private String locationCode;
    @Column(name = "location_name", nullable = false)
    private String locationName;
    @Column(name = "location_name_local")
    private String locationNameLocal;
    @Column(name = "location_type", length = 50)
    private String locationType;
    @Column(name = "building_name")
    private String buildingName;
    @Column(name = "floor_no", length = 50)
    private String floorNo;
    @Column(name = "room_no", length = 50)
    private String roomNo;
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

    @Override public String getPrimaryKeyValue() { return locationId; }
    @Override public void setPrimaryKeyValue(String primaryKeyValue) { locationId = primaryKeyValue; }
    @Override public String getPrimaryKeyFieldName() { return "locationId"; }
}
