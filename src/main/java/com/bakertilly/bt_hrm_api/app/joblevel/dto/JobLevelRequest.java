package com.bakertilly.bt_hrm_api.app.joblevel.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Job level create/update request")
public class JobLevelRequest {
    @NotBlank(message = "Company ID is required") @Size(max = 60)
    private String companyId;
    @NotBlank(message = "Job level code is required") @Size(max = 50)
    private String jobLevelCode;
    @NotBlank(message = "Job level name is required") @Size(max = 255)
    private String jobLevelName;
    @Size(max = 255)
    private String jobLevelNameLocal;
    @Size(max = 100)
    private String jobLevelGroup;
    private Integer levelRank;
    private Integer sortOrder;
    @Size(max = 1000)
    private String description;
}
