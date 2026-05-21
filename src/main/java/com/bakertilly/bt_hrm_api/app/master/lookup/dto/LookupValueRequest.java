package com.bakertilly.bt_hrm_api.app.master.lookup.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Schema(description = "Lookup value create/update request")
public class LookupValueRequest {
    @NotBlank(message = "Lookup group ID is required") @Size(max = 60)
    private String lookupGroupId;
    @NotBlank(message = "Lookup code is required") @Size(max = 100)
    private String lookupCode;
    @NotBlank(message = "Lookup name is required") @Size(max = 255)
    private String lookupName;
    @Size(max = 255)
    private String lookupNameLocal;
    @Size(max = 255)
    private String valueText;
    private BigDecimal valueNumber;
    @Size(max = 50)
    private String colorCode;
    @Size(max = 100)
    private String iconName;
    @Size(max = 1000)
    private String description;
    private Boolean isDefault;
    private Boolean isSystem;
    private Integer sortOrder;
}
