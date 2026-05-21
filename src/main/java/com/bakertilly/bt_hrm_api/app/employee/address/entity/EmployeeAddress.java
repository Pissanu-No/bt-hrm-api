package com.bakertilly.bt_hrm_api.app.employee.address.entity;

import com.bakertilly.bt_hrm_api.app.common.persistence.AuditableSoftDeleteEntity;
import com.bakertilly.bt_hrm_api.app.common.persistence.UuidPrimaryKeyEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "hris_transaction_address", schema = "hris")
public class EmployeeAddress extends AuditableSoftDeleteEntity implements UuidPrimaryKeyEntity {
    @Id
    @Column(name = "address_id", length = 60, nullable = false)
    private String addressId;

    @Column(name = "employee_id", length = 60, nullable = false)
    private String employeeId;

    @Column(name = "address_type", length = 50, nullable = false)
    private String addressType;

    @Column(name = "address_label", length = 100)
    private String addressLabel;

    @Column(name = "house_no", length = 100)
    private String houseNo;

    @Column(name = "village_no", length = 50)
    private String villageNo;

    @Column(name = "village_name")
    private String villageName;

    @Column(name = "building_name")
    private String buildingName;

    @Column(name = "floor_no", length = 50)
    private String floorNo;

    @Column(name = "room_no", length = 50)
    private String roomNo;

    @Column(name = "alley")
    private String alley;

    @Column(name = "road")
    private String road;

    @Column(name = "sub_district", length = 100)
    private String subDistrict;

    @Column(name = "district", length = 100)
    private String district;

    @Column(name = "province", length = 100)
    private String province;

    @Column(name = "postal_code", length = 20)
    private String postalCode;

    @Column(name = "country", length = 100, nullable = false)
    private String country = "Thailand";

    @Column(name = "full_address", length = 1000)
    private String fullAddress;

    @Column(name = "latitude", precision = 10, scale = 7)
    private BigDecimal latitude;

    @Column(name = "longitude", precision = 10, scale = 7)
    private BigDecimal longitude;

    @Column(name = "is_primary", nullable = false)
    private Boolean isPrimary = false;

    @Column(name = "is_same_as_registered_address", nullable = false)
    private Boolean isSameAsRegisteredAddress = false;

    @Column(name = "effective_start_date")
    private LocalDate effectiveStartDate;

    @Column(name = "effective_end_date")
    private LocalDate effectiveEndDate;

    @Column(name = "note", length = 1000)
    private String note;

    @Override
    public String getPrimaryKeyValue() {
        return addressId;
    }

    @Override
    public void setPrimaryKeyValue(String primaryKeyValue) {
        addressId = primaryKeyValue;
    }

    @Override
    public String getPrimaryKeyFieldName() {
        return "addressId";
    }
}
