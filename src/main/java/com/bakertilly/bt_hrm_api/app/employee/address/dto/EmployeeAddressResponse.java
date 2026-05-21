package com.bakertilly.bt_hrm_api.app.employee.address.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@Schema(description = "Employee address response")
public class EmployeeAddressResponse {
    private String addressId;
    private String employeeId;
    private String addressType;
    private String addressLabel;
    private String houseNo;
    private String villageNo;
    private String villageName;
    private String buildingName;
    private String floorNo;
    private String roomNo;
    private String alley;
    private String road;
    private String subDistrict;
    private String district;
    private String province;
    private String postalCode;
    private String country;
    private String fullAddress;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private Boolean isPrimary;
    private Boolean isSameAsRegisteredAddress;
    private LocalDate effectiveStartDate;
    private LocalDate effectiveEndDate;
    private String note;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
