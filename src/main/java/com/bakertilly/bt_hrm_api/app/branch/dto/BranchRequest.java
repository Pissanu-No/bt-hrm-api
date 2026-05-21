package com.bakertilly.bt_hrm_api.app.branch.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Branch create/update request")
public class BranchRequest {
    @NotBlank(message = "Company ID is required")
    @Size(max = 60)
    private String companyId;

    @NotBlank(message = "Branch code is required")
    @Size(max = 50)
    private String branchCode;

    @NotBlank(message = "Branch name is required")
    @Size(max = 255)
    private String branchName;

    @Size(max = 255)
    private String branchNameLocal;

    @Size(max = 50)
    private String branchType;

    @Email
    @Size(max = 255)
    private String email;

    @Size(max = 50)
    private String phone;

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

    @Size(max = 1000)
    private String description;
}
