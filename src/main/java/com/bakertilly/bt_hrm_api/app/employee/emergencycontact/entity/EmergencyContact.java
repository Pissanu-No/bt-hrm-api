package com.bakertilly.bt_hrm_api.app.employee.emergencycontact.entity;

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
@Table(name = "hris_transaction_emergency_contact", schema = "hris")
public class EmergencyContact extends AuditableSoftDeleteEntity implements UuidPrimaryKeyEntity {
    @Id
    @Column(name = "emergency_contact_id", length = 60, nullable = false)
    private String emergencyContactId;

    @Column(name = "employee_id", length = 60, nullable = false)
    private String employeeId;

    @Column(name = "contact_name", nullable = false)
    private String contactName;

    @Column(name = "relationship", length = 100, nullable = false)
    private String relationship;

    @Column(name = "mobile_phone", length = 50)
    private String mobilePhone;

    @Column(name = "alternate_phone", length = 50)
    private String alternatePhone;

    @Column(name = "email")
    private String email;

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

    @Column(name = "priority_no", nullable = false)
    private Integer priorityNo = 1;

    @Column(name = "is_primary", nullable = false)
    private Boolean isPrimary = false;

    @Column(name = "note", length = 1000)
    private String note;

    @Override
    public String getPrimaryKeyValue() {
        return emergencyContactId;
    }

    @Override
    public void setPrimaryKeyValue(String primaryKeyValue) {
        emergencyContactId = primaryKeyValue;
    }

    @Override
    public String getPrimaryKeyFieldName() {
        return "emergencyContactId";
    }
}
