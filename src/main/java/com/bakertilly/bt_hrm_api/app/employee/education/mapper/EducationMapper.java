package com.bakertilly.bt_hrm_api.app.employee.education.mapper;

import com.bakertilly.bt_hrm_api.app.employee.education.dto.EducationRequest;
import com.bakertilly.bt_hrm_api.app.employee.education.dto.EducationResponse;
import com.bakertilly.bt_hrm_api.app.employee.education.entity.Education;
import org.springframework.stereotype.Component;

@Component
public class EducationMapper {
    public void updateEntity(Education entity, EducationRequest request) {
        entity.setEducationLevel(request.getEducationLevel());
        entity.setInstitutionName(request.getInstitutionName());
        entity.setInstitutionNameLocal(request.getInstitutionNameLocal());
        entity.setFaculty(request.getFaculty());
        entity.setMajor(request.getMajor());
        entity.setDegreeName(request.getDegreeName());
        entity.setDegreeNameLocal(request.getDegreeNameLocal());
        entity.setCountry(request.getCountry());
        entity.setStartDate(request.getStartDate());
        entity.setGraduationDate(request.getGraduationDate());
        entity.setGpa(request.getGpa());
        entity.setIsHighestEducation(Boolean.TRUE.equals(request.getIsHighestEducation()));
        entity.setDescription(request.getDescription());
    }

    public EducationResponse toResponse(Education entity) {
        return EducationResponse.builder()
                .educationId(entity.getEducationId())
                .employeeId(entity.getEmployeeId())
                .educationLevel(entity.getEducationLevel())
                .institutionName(entity.getInstitutionName())
                .institutionNameLocal(entity.getInstitutionNameLocal())
                .faculty(entity.getFaculty())
                .major(entity.getMajor())
                .degreeName(entity.getDegreeName())
                .degreeNameLocal(entity.getDegreeNameLocal())
                .country(entity.getCountry())
                .startDate(entity.getStartDate())
                .graduationDate(entity.getGraduationDate())
                .gpa(entity.getGpa())
                .isHighestEducation(entity.getIsHighestEducation())
                .description(entity.getDescription())
                .isActive(entity.getIsActive())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
