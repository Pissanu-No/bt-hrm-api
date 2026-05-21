package com.bakertilly.bt_hrm_api.app.branch.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@Schema(description = "Branch response")
public class BranchResponse {
    private String branchId;
    private String companyId;
    private String branchCode;
    private String branchName;
    private String branchNameLocal;
    private String branchType;
    private String email;
    private String phone;
    private String addressLine1;
    private String addressLine2;
    private String province;
    private String country;
    private String postalCode;
    private Integer sortOrder;
    private String description;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
