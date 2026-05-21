package com.bakertilly.bt_hrm_api.app.employee.entity;

import com.bakertilly.bt_hrm_api.app.common.persistence.AuditableSoftDeleteEntity;
import com.bakertilly.bt_hrm_api.app.common.persistence.UuidPrimaryKeyEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "hris_transaction_employee", schema = "hris")
public class Employee extends AuditableSoftDeleteEntity implements UuidPrimaryKeyEntity {
    @Id
    @Column(name = "employee_id", length = 60, nullable = false)
    private String employeeId;

    @Column(name = "employee_code", length = 50, nullable = false)
    private String employeeCode;

    @Column(name = "title_name", length = 50)
    private String titleName;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "title_name_local", length = 50)
    private String titleNameLocal;

    @Column(name = "first_name_local")
    private String firstNameLocal;

    @Column(name = "middle_name_local")
    private String middleNameLocal;

    @Column(name = "last_name_local")
    private String lastNameLocal;

    @Column(name = "preferred_name")
    private String preferredName;

    @Column(name = "gender", length = 30)
    private String gender;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "nationality", length = 100)
    private String nationality;

    @Column(name = "address", length = 500)
    private String address;

    @Column(name = "marital_status", length = 50)
    private String maritalStatus;

    @Column(name = "blood_type", length = 20)
    private String bloodType;

    @Column(name = "national_id", length = 100)
    private String nationalId;

    @Column(name = "passport_no", length = 100)
    private String passportNo;

    @Column(name = "tax_no", length = 100)
    private String taxNo;

    @Column(name = "social_security_no", length = 100)
    private String socialSecurityNo;

    @Column(name = "highest_education_level", length = 100)
    private String highestEducationLevel;

    @Column(name = "registered_address_id", length = 60)
    private String registeredAddressId;

    @Column(name = "contact_address_id", length = 60)
    private String contactAddressId;

    @Column(name = "personal_email")
    private String personalEmail;

    @Column(name = "work_email")
    private String workEmail;

    @Column(name = "mobile_phone", length = 50)
    private String mobilePhone;

    @Column(name = "employee_status", length = 50, nullable = false)
    private String employeeStatus;

    @Column(name = "profile_image_url", length = 1000)
    private String profileImageUrl;

    @Override
    public String getPrimaryKeyValue() {
        return employeeId;
    }

    @Override
    public void setPrimaryKeyValue(String primaryKeyValue) {
        employeeId = primaryKeyValue;
    }

    @Override
    public String getPrimaryKeyFieldName() {
        return "employeeId";
    }
}
