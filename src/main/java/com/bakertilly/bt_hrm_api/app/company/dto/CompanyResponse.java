package com.bakertilly.bt_hrm_api.app.company.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@Schema(description = "Company response")
public class CompanyResponse {
    private String companyId;
    private String companyCode;
    private String companyName;
    private String companyNameLocal;
    private String companyShortName;
    private String companyLogoUrl;
    private String taxId;
    private String registrationNo;
    private String email;
    private String phone;
    private String website;
    private String addressLine1;
    private String addressLine2;
    private String province;
    private String country;
    private String postalCode;
    private Integer sortOrder;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
