package com.bakertilly.bt_hrm_api.app.employee.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@Schema(description = "Employee response")
public class EmployeeResponse {
    private String employeeId;
    private String employeeCode;
    private String titleName;
    private String firstName;
    private String middleName;
    private String lastName;
    private String titleNameLocal;
    private String firstNameLocal;
    private String middleNameLocal;
    private String lastNameLocal;
    private String preferredName;
    private String gender;
    private LocalDate birthDate;
    private String nationality;
    private String address;
    private String maritalStatus;
    private String bloodType;
    private String nationalId;
    private String passportNo;
    private String taxNo;
    private String socialSecurityNo;
    private String highestEducationLevel;
    private String registeredAddressId;
    private String contactAddressId;
    private String personalEmail;
    private String workEmail;
    private String mobilePhone;
    private String employeeStatus;
    private String profileImageUrl;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
