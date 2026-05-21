package com.bakertilly.bt_hrm_api.app.employee.education.entity;

import com.bakertilly.bt_hrm_api.app.common.persistence.AuditableSoftDeleteEntity;
import com.bakertilly.bt_hrm_api.app.common.persistence.UuidPrimaryKeyEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "hris_transaction_education", schema = "hris")
public class Education extends AuditableSoftDeleteEntity implements UuidPrimaryKeyEntity {
    @Id
    @Column(name = "education_id", length = 60, nullable = false)
    private String educationId;

    @Column(name = "employee_id", length = 60, nullable = false)
    private String employeeId;

    @Column(name = "education_level", length = 100, nullable = false)
    private String educationLevel;

    @Column(name = "institution_name", nullable = false)
    private String institutionName;

    @Column(name = "institution_name_local")
    private String institutionNameLocal;

    @Column(name = "faculty")
    private String faculty;

    @Column(name = "major")
    private String major;

    @Column(name = "degree_name")
    private String degreeName;

    @Column(name = "degree_name_local")
    private String degreeNameLocal;

    @Column(name = "country", length = 100)
    private String country;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "graduation_date")
    private LocalDate graduationDate;

    @Column(name = "gpa", precision = 4, scale = 2)
    private BigDecimal gpa;

    @Column(name = "is_highest_education", nullable = false)
    private Boolean isHighestEducation = false;

    @Column(name = "description", length = 1000)
    private String description;

    @Override
    public String getPrimaryKeyValue() {
        return educationId;
    }

    @Override
    public void setPrimaryKeyValue(String primaryKeyValue) {
        educationId = primaryKeyValue;
    }

    @Override
    public String getPrimaryKeyFieldName() {
        return "educationId";
    }
}
