package com.bakertilly.bt_hrm_api.app.company.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Company create/update request")
public class CompanyRequest {
    @NotBlank(message = "Company code is required")
    @Size(max = 50)
    @Schema(description = "Company code", example = "BT")
    private String companyCode;

    @NotBlank(message = "Company name is required")
    @Size(max = 255)
    @Schema(description = "Company name", example = "Baker Tilly")
    private String companyName;

    @Size(max = 255)
    private String companyNameLocal;

    @Size(max = 100)
    private String companyShortName;

    @Size(max = 1000)
    private String companyLogoUrl;

    @Size(max = 50)
    private String taxId;

    @Size(max = 100)
    private String registrationNo;

    @Email
    @Size(max = 255)
    private String email;

    @Size(max = 50)
    private String phone;

    @Size(max = 255)
    private String website;

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

    private Integer sortOrder;
}
