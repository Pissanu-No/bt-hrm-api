package com.bakertilly.bt_hrm_api.app.location.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Location create/update request")
public class LocationRequest {
    @NotBlank(message = "Company ID is required") @Size(max = 60)
    private String companyId;
    @Size(max = 60)
    private String branchId;
    @NotBlank(message = "Location code is required") @Size(max = 50)
    private String locationCode;
    @NotBlank(message = "Location name is required") @Size(max = 255)
    private String locationName;
    @Size(max = 255)
    private String locationNameLocal;
    @Size(max = 50)
    private String locationType;
    @Size(max = 255)
    private String buildingName;
    @Size(max = 50)
    private String floorNo;
    @Size(max = 50)
    private String roomNo;
    @Size(max = 500)
    private String addressLine1;
    @Size(max = 500)
    private String addressLine2;
    @Size(max = 100)
    private String province;
    @Size(max = 100)
    private String country;
    @Size(max = 20)
    private String postalCode;
    private Integer sortOrder;
    @Size(max = 1000)
    private String description;
}
