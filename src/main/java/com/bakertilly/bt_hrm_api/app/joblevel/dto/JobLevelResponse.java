package com.bakertilly.bt_hrm_api.app.joblevel.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@Schema(description = "Job level response")
public class JobLevelResponse {
    private String jobLevelId;
    private String companyId;
    private String jobLevelCode;
    private String jobLevelName;
    private String jobLevelNameLocal;
    private String jobLevelGroup;
    private Integer levelRank;
    private Integer sortOrder;
    private String description;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
