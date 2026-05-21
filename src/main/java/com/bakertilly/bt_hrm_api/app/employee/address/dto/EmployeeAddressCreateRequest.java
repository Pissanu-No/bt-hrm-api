package com.bakertilly.bt_hrm_api.app.employee.address.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Schema(description = "Employee address create request")
public class EmployeeAddressCreateRequest {
    @NotBlank(message = "Address type is required")
    @Size(max = 50)
    private String addressType;

    @Size(max = 100)
    private String addressLabel;

    @Size(max = 100)
    private String houseNo;

    @Size(max = 50)
    private String villageNo;

    @Size(max = 255)
    private String villageName;

    @Size(max = 255)
    private String buildingName;

    @Size(max = 50)
    private String floorNo;

    @Size(max = 50)
    private String roomNo;

    @Size(max = 255)
    private String alley;

    @Size(max = 255)
    private String road;

    @Size(max = 100)
    private String subDistrict;

    @Size(max = 100)
    private String district;

    @Size(max = 100)
    private String province;

    @Size(max = 20)
    private String postalCode;

    @Size(max = 100)
    private String country;

    @Size(max = 1000)
    private String fullAddress;

    @DecimalMin(value = "-90.0", message = "Latitude must be between -90 and 90")
    @DecimalMax(value = "90.0", message = "Latitude must be between -90 and 90")
    private BigDecimal latitude;

    @DecimalMin(value = "-180.0", message = "Longitude must be between -180 and 180")
    @DecimalMax(value = "180.0", message = "Longitude must be between -180 and 180")
    private BigDecimal longitude;

    private Boolean isPrimary;

    private Boolean isSameAsRegisteredAddress;

    private LocalDate effectiveStartDate;

    private LocalDate effectiveEndDate;

    @Size(max = 1000)
    private String note;
}
