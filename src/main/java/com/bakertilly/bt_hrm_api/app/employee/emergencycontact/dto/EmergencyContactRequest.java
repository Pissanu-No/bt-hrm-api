package com.bakertilly.bt_hrm_api.app.employee.emergencycontact.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Employee emergency contact create/update request")
public class EmergencyContactRequest {
    @NotBlank(message = "Contact name is required")
    @Size(max = 255)
    private String contactName;

    @NotBlank(message = "Relationship is required")
    @Size(max = 100)
    private String relationship;

    @Size(max = 50)
    private String mobilePhone;

    @Size(max = 50)
    private String alternatePhone;

    @Email
    @Size(max = 255)
    private String email;

    @Size(max = 500)
    private String addressLine1;

    @Size(max = 500)
    private String addressLine2;

    @Size(max = 100)
    private String province;

    @Size(max = 100)
    private String country;

    @Size(max = 20)
    private String postalCode;

    @Min(1)
    private Integer priorityNo;

    private Boolean isPrimary;

    @Size(max = 1000)
    private String note;
}
