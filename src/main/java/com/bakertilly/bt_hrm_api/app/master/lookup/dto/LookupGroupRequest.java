package com.bakertilly.bt_hrm_api.app.master.lookup.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Lookup group create/update request")
public class LookupGroupRequest {
    @NotBlank(message = "Lookup group code is required") @Size(max = 100)
    private String lookupGroupCode;
    @NotBlank(message = "Lookup group name is required") @Size(max = 255)
    private String lookupGroupName;
    @Size(max = 255)
    private String lookupGroupNameLocal;
    @NotBlank(message = "Module code is required") @Size(max = 100)
    private String moduleCode;
    @Size(max = 1000)
    private String description;
    private Boolean isSystem;
    private Integer sortOrder;
}
