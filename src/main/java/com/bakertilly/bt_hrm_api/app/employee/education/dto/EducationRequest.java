package com.bakertilly.bt_hrm_api.app.employee.education.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Schema(description = "Employee education create/update request")
public class EducationRequest {
    @NotBlank(message = "Education level is required")
    @Size(max = 100)
    private String educationLevel;

    @NotBlank(message = "Institution name is required")
    @Size(max = 255)
    private String institutionName;

    @Size(max = 255)
    private String institutionNameLocal;

    @Size(max = 255)
    private String faculty;

    @Size(max = 255)
    private String major;

    @Size(max = 255)
    private String degreeName;

    @Size(max = 255)
    private String degreeNameLocal;

    @Size(max = 100)
    private String country;

    private LocalDate startDate;

    private LocalDate graduationDate;

    @Digits(integer = 2, fraction = 2)
    @DecimalMin(value = "0.00", message = "GPA must be between 0.00 and 4.00")
    @DecimalMax(value = "4.00", message = "GPA must be between 0.00 and 4.00")
    private BigDecimal gpa;

    private Boolean isHighestEducation;

    @Size(max = 1000)
    private String description;
}
