package com.bakertilly.bt_hrm_api.app.department.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@Schema(description = "Department response")
public class DepartmentResponse {
    private String departmentId;
    private String companyId;
    private String branchId;
    private String costCenterId;
    private String parentDepartmentId;
    private String departmentCode;
    private String departmentName;
    private String departmentNameLocal;
    private String departmentType;
    private String description;
    private Integer sortOrder;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
