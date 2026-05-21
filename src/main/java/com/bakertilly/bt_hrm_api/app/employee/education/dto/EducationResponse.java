package com.bakertilly.bt_hrm_api.app.employee.education.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@Schema(description = "Employee education response")
public class EducationResponse {
    private String educationId;
    private String employeeId;
    private String educationLevel;
    private String institutionName;
    private String institutionNameLocal;
    private String faculty;
    private String major;
    private String degreeName;
    private String degreeNameLocal;
    private String country;
    private LocalDate startDate;
    private LocalDate graduationDate;
    private BigDecimal gpa;
    private Boolean isHighestEducation;
    private String description;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
