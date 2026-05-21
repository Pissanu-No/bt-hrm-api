package com.bakertilly.bt_hrm_api.app.costcenter.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Cost center create/update request")
public class CostCenterRequest {
    @NotBlank(message = "Company ID is required") @Size(max = 60)
    private String companyId;
    @NotBlank(message = "Cost center code is required") @Size(max = 50)
    private String costCenterCode;
    @NotBlank(message = "Cost center name is required") @Size(max = 255)
    private String costCenterName;
    @Size(max = 255)
    private String costCenterNameLocal;
    @Size(max = 100)
    private String costCenterType;
    private Integer sortOrder;
    @Size(max = 1000)
    private String description;
}
