package com.bakertilly.bt_hrm_api.app.common.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Field validation error")
public class ApiFieldError {
    @Schema(description = "Field name", example = "companyCode")
    private String field;

    @Schema(description = "Error message", example = "Company code is required")
    private String message;
}
