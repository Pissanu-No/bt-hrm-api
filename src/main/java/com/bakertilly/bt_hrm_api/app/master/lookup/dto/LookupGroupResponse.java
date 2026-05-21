package com.bakertilly.bt_hrm_api.app.master.lookup.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@Schema(description = "Lookup group response")
public class LookupGroupResponse {
    private String lookupGroupId;
    private String lookupGroupCode;
    private String lookupGroupName;
    private String lookupGroupNameLocal;
    private String moduleCode;
    private String description;
    private Boolean isSystem;
    private Integer sortOrder;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
