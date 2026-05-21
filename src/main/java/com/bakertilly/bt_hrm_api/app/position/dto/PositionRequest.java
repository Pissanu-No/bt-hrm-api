package com.bakertilly.bt_hrm_api.app.position.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Position create/update request")
public class PositionRequest {
    @NotBlank(message = "Company ID is required")
    @Size(max = 60)
    private String companyId;

    @Size(max = 60)
    private String departmentId;

    @Size(max = 60)
    private String jobLevelId;

    @Size(max = 60)
    private String jobFamilyId;

    @NotBlank(message = "Position code is required")
    @Size(max = 50)
    private String positionCode;

    @NotBlank(message = "Position name is required")
    @Size(max = 255)
    private String positionName;

    @Size(max = 255)
    private String positionNameLocal;

    @Size(max = 100)
    private String positionType;

    @Size(max = 1000)
    private String description;

    private Integer sortOrder;

    @Schema(description = "Whether this position is a manager position", example = "false")
    private Boolean isManagerPosition;
}
