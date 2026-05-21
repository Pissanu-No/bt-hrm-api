package com.bakertilly.bt_hrm_api.app.employee.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@Schema(description = "Employee list response without sensitive personal identifiers")
public class EmployeeListResponse {
    private String employeeId;
    private String employeeCode;
    private String titleName;
    private String firstName;
    private String middleName;
    private String lastName;
    private String preferredName;
    private String workEmail;
    private String mobilePhone;
    private String employeeStatus;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
