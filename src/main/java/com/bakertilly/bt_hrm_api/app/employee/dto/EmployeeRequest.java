package com.bakertilly.bt_hrm_api.app.employee.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
@Schema(description = "Employee create/update request")
public class EmployeeRequest {
    @NotBlank(message = "Employee code is required")
    @Size(max = 50)
    private String employeeCode;

    @Size(max = 50)
    private String titleName;

    @NotBlank(message = "First name is required")
    @Size(max = 255)
    private String firstName;

    @Size(max = 255)
    private String middleName;

    @NotBlank(message = "Last name is required")
    @Size(max = 255)
    private String lastName;

    @Size(max = 50)
    private String titleNameLocal;

    @Size(max = 255)
    private String firstNameLocal;

    @Size(max = 255)
    private String middleNameLocal;

    @Size(max = 255)
    private String lastNameLocal;

    @Size(max = 255)
    private String preferredName;

    @Size(max = 30)
    private String gender;

    private LocalDate birthDate;

    @Size(max = 100)
    private String nationality;

    @Size(max = 500)
    @Schema(description = "Employee address", example = "123 Main Road, Bangkok")
    private String address;

    @Size(max = 50)
    @Schema(description = "Marital status", example = "SINGLE")
    private String maritalStatus;

    @Size(max = 20)
    @Schema(description = "Blood type", example = "O")
    private String bloodType;

    @Size(max = 100)
    @Schema(description = "National ID", example = "1234567890123")
    private String nationalId;

    @Size(max = 100)
    @Schema(description = "Passport number", example = "AA1234567")
    private String passportNo;

    @Size(max = 100)
    @Schema(description = "Tax number", example = "1234567890")
    private String taxNo;

    @Size(max = 100)
    @Schema(description = "Social security number", example = "SSO123456")
    private String socialSecurityNo;

    @Size(max = 100)
    @Schema(description = "Highest education level", example = "BACHELOR")
    private String highestEducationLevel;

    @Size(max = 60)
    @Schema(description = "Registered address ID")
    private String registeredAddressId;

    @Size(max = 60)
    @Schema(description = "Contact address ID")
    private String contactAddressId;

    @Email
    @Size(max = 255)
    private String personalEmail;

    @Email
    @Size(max = 255)
    private String workEmail;

    @Size(max = 50)
    private String mobilePhone;

    @NotBlank(message = "Employee status is required")
    @Size(max = 50)
    private String employeeStatus;

    @Size(max = 1000)
    private String profileImageUrl;

}
