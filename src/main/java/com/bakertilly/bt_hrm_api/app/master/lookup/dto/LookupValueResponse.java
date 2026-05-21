package com.bakertilly.bt_hrm_api.app.master.lookup.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@Schema(description = "Lookup value response")
public class LookupValueResponse {
    private String lookupValueId;
    private String lookupGroupId;
    private String lookupCode;
    private String lookupName;
    private String lookupNameLocal;
    private String valueText;
    private BigDecimal valueNumber;
    private String colorCode;
    private String iconName;
    private String description;
    private Boolean isDefault;
    private Boolean isSystem;
    private Integer sortOrder;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
