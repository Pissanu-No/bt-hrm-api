package com.bakertilly.bt_hrm_api.app.department.mapper;

import com.bakertilly.bt_hrm_api.app.department.dto.DepartmentRequest;
import com.bakertilly.bt_hrm_api.app.department.dto.DepartmentResponse;
import com.bakertilly.bt_hrm_api.app.department.entity.Department;
import org.springframework.stereotype.Component;

@Component
public class DepartmentMapper {
    public void updateEntity(Department entity, DepartmentRequest request) {
        entity.setCompanyId(request.getCompanyId());
        entity.setBranchId(request.getBranchId());
        entity.setCostCenterId(request.getCostCenterId());
        entity.setParentDepartmentId(request.getParentDepartmentId());
        entity.setDepartmentCode(request.getDepartmentCode());
        entity.setDepartmentName(request.getDepartmentName());
        entity.setDepartmentNameLocal(request.getDepartmentNameLocal());
        entity.setDepartmentType(request.getDepartmentType());
        entity.setDescription(request.getDescription());
        entity.setSortOrder(request.getSortOrder() == null ? 0 : request.getSortOrder());
    }

    public DepartmentResponse toResponse(Department entity) {
        return DepartmentResponse.builder()
                .departmentId(entity.getDepartmentId())
                .companyId(entity.getCompanyId())
                .branchId(entity.getBranchId())
                .costCenterId(entity.getCostCenterId())
                .parentDepartmentId(entity.getParentDepartmentId())
                .departmentCode(entity.getDepartmentCode())
                .departmentName(entity.getDepartmentName())
                .departmentNameLocal(entity.getDepartmentNameLocal())
                .departmentType(entity.getDepartmentType())
                .description(entity.getDescription())
                .sortOrder(entity.getSortOrder())
                .isActive(entity.getIsActive())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
