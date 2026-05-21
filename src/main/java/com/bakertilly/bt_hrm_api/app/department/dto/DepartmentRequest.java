package com.bakertilly.bt_hrm_api.app.department.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Department create/update request")
public class DepartmentRequest {
    @NotBlank(message = "Company ID is required")
    @Size(max = 60)
    private String companyId;

    @Size(max = 60)
    private String branchId;

    @Size(max = 60)
    private String costCenterId;

    @Size(max = 60)
    private String parentDepartmentId;

    @NotBlank(message = "Department code is required")
    @Size(max = 50)
    private String departmentCode;

    @NotBlank(message = "Department name is required")
    @Size(max = 255)
    private String departmentName;

    @Size(max = 255)
    private String departmentNameLocal;

    @Size(max = 100)
    private String departmentType;

    @Size(max = 1000)
    private String description;

    private Integer sortOrder;
}
