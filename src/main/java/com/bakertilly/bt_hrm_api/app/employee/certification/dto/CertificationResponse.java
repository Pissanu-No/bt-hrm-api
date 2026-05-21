package com.bakertilly.bt_hrm_api.app.employee.certification.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@Schema(description = "Employee certification response")
public class CertificationResponse {
    private String certificationId;
    private String employeeId;
    private String certificationName;
    private String certificationCode;
    private String issuingOrganization;
    private String credentialId;
    private String credentialUrl;
    private LocalDate issuedDate;
    private LocalDate expiredDate;
    private Boolean neverExpires;
    private String certificationStatus;
    private String description;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
