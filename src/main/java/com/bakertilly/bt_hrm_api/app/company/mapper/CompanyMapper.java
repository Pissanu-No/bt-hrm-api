package com.bakertilly.bt_hrm_api.app.company.mapper;

import com.bakertilly.bt_hrm_api.app.company.dto.CompanyRequest;
import com.bakertilly.bt_hrm_api.app.company.dto.CompanyResponse;
import com.bakertilly.bt_hrm_api.app.company.entity.Company;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper {
    public void updateEntity(Company entity, CompanyRequest request) {
        entity.setCompanyCode(request.getCompanyCode());
        entity.setCompanyName(request.getCompanyName());
        entity.setCompanyNameLocal(request.getCompanyNameLocal());
        entity.setCompanyShortName(request.getCompanyShortName());
        entity.setCompanyLogoUrl(request.getCompanyLogoUrl());
        entity.setTaxId(request.getTaxId());
        entity.setRegistrationNo(request.getRegistrationNo());
        entity.setEmail(request.getEmail());
        entity.setPhone(request.getPhone());
        entity.setWebsite(request.getWebsite());
        entity.setAddressLine1(request.getAddressLine1());
        entity.setAddressLine2(request.getAddressLine2());
        entity.setProvince(request.getProvince());
        entity.setCountry(request.getCountry());
        entity.setPostalCode(request.getPostalCode());
        entity.setSortOrder(request.getSortOrder() == null ? 0 : request.getSortOrder());
    }

    public CompanyResponse toResponse(Company entity) {
        return CompanyResponse.builder()
                .companyId(entity.getCompanyId())
                .companyCode(entity.getCompanyCode())
                .companyName(entity.getCompanyName())
                .companyNameLocal(entity.getCompanyNameLocal())
                .companyShortName(entity.getCompanyShortName())
                .companyLogoUrl(entity.getCompanyLogoUrl())
                .taxId(entity.getTaxId())
                .registrationNo(entity.getRegistrationNo())
                .email(entity.getEmail())
                .phone(entity.getPhone())
                .website(entity.getWebsite())
                .addressLine1(entity.getAddressLine1())
                .addressLine2(entity.getAddressLine2())
                .province(entity.getProvince())
                .country(entity.getCountry())
                .postalCode(entity.getPostalCode())
                .sortOrder(entity.getSortOrder())
                .isActive(entity.getIsActive())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
