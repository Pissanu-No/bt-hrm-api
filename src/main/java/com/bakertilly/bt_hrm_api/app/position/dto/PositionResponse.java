package com.bakertilly.bt_hrm_api.app.position.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@Schema(description = "Position response")
public class PositionResponse {
    private String positionId;
    private String companyId;
    private String departmentId;
    private String jobLevelId;
    private String jobFamilyId;
    private String positionCode;
    private String positionName;
    private String positionNameLocal;
    private String positionType;
    private String description;
    private Integer sortOrder;
    private Boolean isManagerPosition;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
