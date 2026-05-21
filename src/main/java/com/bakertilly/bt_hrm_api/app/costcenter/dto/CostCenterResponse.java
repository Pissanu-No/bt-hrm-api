package com.bakertilly.bt_hrm_api.app.costcenter.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@Schema(description = "Cost center response")
public class CostCenterResponse {
    private String costCenterId;
    private String companyId;
    private String costCenterCode;
    private String costCenterName;
    private String costCenterNameLocal;
    private String costCenterType;
    private Integer sortOrder;
    private String description;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
