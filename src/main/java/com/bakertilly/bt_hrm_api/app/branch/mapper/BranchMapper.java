package com.bakertilly.bt_hrm_api.app.branch.mapper;

import com.bakertilly.bt_hrm_api.app.branch.dto.BranchRequest;
import com.bakertilly.bt_hrm_api.app.branch.dto.BranchResponse;
import com.bakertilly.bt_hrm_api.app.branch.entity.Branch;
import org.springframework.stereotype.Component;

@Component
public class BranchMapper {
    public void updateEntity(Branch entity, BranchRequest request) {
        entity.setCompanyId(request.getCompanyId());
        entity.setBranchCode(request.getBranchCode());
        entity.setBranchName(request.getBranchName());
        entity.setBranchNameLocal(request.getBranchNameLocal());
        entity.setBranchType(request.getBranchType());
        entity.setEmail(request.getEmail());
        entity.setPhone(request.getPhone());
        entity.setAddressLine1(request.getAddressLine1());
        entity.setAddressLine2(request.getAddressLine2());
        entity.setProvince(request.getProvince());
        entity.setCountry(request.getCountry());
        entity.setPostalCode(request.getPostalCode());
        entity.setSortOrder(request.getSortOrder() == null ? 0 : request.getSortOrder());
        entity.setDescription(request.getDescription());
    }

    public BranchResponse toResponse(Branch entity) {
        return BranchResponse.builder()
                .branchId(entity.getBranchId())
                .companyId(entity.getCompanyId())
                .branchCode(entity.getBranchCode())
                .branchName(entity.getBranchName())
                .branchNameLocal(entity.getBranchNameLocal())
                .branchType(entity.getBranchType())
                .email(entity.getEmail())
                .phone(entity.getPhone())
                .addressLine1(entity.getAddressLine1())
                .addressLine2(entity.getAddressLine2())
                .province(entity.getProvince())
                .country(entity.getCountry())
                .postalCode(entity.getPostalCode())
                .sortOrder(entity.getSortOrder())
                .description(entity.getDescription())
                .isActive(entity.getIsActive())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
