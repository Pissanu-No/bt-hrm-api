package com.bakertilly.bt_hrm_api.app.employee.certification.mapper;

import com.bakertilly.bt_hrm_api.app.employee.certification.dto.CertificationRequest;
import com.bakertilly.bt_hrm_api.app.employee.certification.dto.CertificationResponse;
import com.bakertilly.bt_hrm_api.app.employee.certification.entity.Certification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class CertificationMapper {
    public void updateEntity(Certification entity, CertificationRequest request) {
        entity.setCertificationName(request.getCertificationName());
        entity.setCertificationCode(request.getCertificationCode());
        entity.setIssuingOrganization(request.getIssuingOrganization());
        entity.setCredentialId(request.getCredentialId());
        entity.setCredentialUrl(request.getCredentialUrl());
        entity.setIssuedDate(request.getIssuedDate());
        entity.setNeverExpires(Boolean.TRUE.equals(request.getNeverExpires()));
        entity.setExpiredDate(Boolean.TRUE.equals(request.getNeverExpires()) ? null : request.getExpiredDate());
        entity.setCertificationStatus(StringUtils.hasText(request.getCertificationStatus())
                ? request.getCertificationStatus()
                : "ACTIVE");
        entity.setDescription(request.getDescription());
    }

    public CertificationResponse toResponse(Certification entity) {
        return CertificationResponse.builder()
                .certificationId(entity.getCertificationId())
                .employeeId(entity.getEmployeeId())
                .certificationName(entity.getCertificationName())
                .certificationCode(entity.getCertificationCode())
                .issuingOrganization(entity.getIssuingOrganization())
                .credentialId(entity.getCredentialId())
                .credentialUrl(entity.getCredentialUrl())
                .issuedDate(entity.getIssuedDate())
                .expiredDate(entity.getExpiredDate())
                .neverExpires(entity.getNeverExpires())
                .certificationStatus(entity.getCertificationStatus())
                .description(entity.getDescription())
                .isActive(entity.getIsActive())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
