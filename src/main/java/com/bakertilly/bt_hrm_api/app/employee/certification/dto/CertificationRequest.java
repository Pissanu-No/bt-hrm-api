package com.bakertilly.bt_hrm_api.app.employee.certification.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
@Schema(description = "Employee certification create/update request")
public class CertificationRequest {
    @NotBlank(message = "Certification name is required")
    @Size(max = 255)
    private String certificationName;

    @Size(max = 100)
    private String certificationCode;

    @Size(max = 255)
    private String issuingOrganization;

    @Size(max = 255)
    private String credentialId;

    @Size(max = 1000)
    private String credentialUrl;

    private LocalDate issuedDate;

    private LocalDate expiredDate;

    private Boolean neverExpires;

    @Size(max = 50)
    private String certificationStatus;

    @Size(max = 1000)
    private String description;
}
