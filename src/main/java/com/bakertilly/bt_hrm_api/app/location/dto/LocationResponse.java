package com.bakertilly.bt_hrm_api.app.location.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@Schema(description = "Location response")
public class LocationResponse {
    private String locationId;
    private String companyId;
    private String branchId;
    private String locationCode;
    private String locationName;
    private String locationNameLocal;
    private String locationType;
    private String buildingName;
    private String floorNo;
    private String roomNo;
    private String addressLine1;
    private String addressLine2;
    private String province;
    private String country;
    private String postalCode;
    private Integer sortOrder;
    private String description;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
