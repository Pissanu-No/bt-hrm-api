package com.bakertilly.bt_hrm_api.app.employee.emergencycontact.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@Schema(description = "Employee emergency contact response")
public class EmergencyContactResponse {
    private String emergencyContactId;
    private String employeeId;
    private String contactName;
    private String relationship;
    private String mobilePhone;
    private String alternatePhone;
    private String email;
    private String addressLine1;
    private String addressLine2;
    private String province;
    private String country;
    private String postalCode;
    private Integer priorityNo;
    private Boolean isPrimary;
    private String note;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
