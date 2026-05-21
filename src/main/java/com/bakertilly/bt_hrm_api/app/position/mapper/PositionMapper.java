package com.bakertilly.bt_hrm_api.app.position.mapper;

import com.bakertilly.bt_hrm_api.app.position.dto.PositionRequest;
import com.bakertilly.bt_hrm_api.app.position.dto.PositionResponse;
import com.bakertilly.bt_hrm_api.app.position.entity.Position;
import org.springframework.stereotype.Component;

@Component
public class PositionMapper {
    public void updateEntity(Position entity, PositionRequest request) {
        entity.setCompanyId(request.getCompanyId());
        entity.setDepartmentId(request.getDepartmentId());
        entity.setJobLevelId(request.getJobLevelId());
        entity.setJobFamilyId(request.getJobFamilyId());
        entity.setPositionCode(request.getPositionCode());
        entity.setPositionName(request.getPositionName());
        entity.setPositionNameLocal(request.getPositionNameLocal());
        entity.setPositionType(request.getPositionType());
        entity.setDescription(request.getDescription());
        entity.setSortOrder(request.getSortOrder() == null ? 0 : request.getSortOrder());
        entity.setIsManagerPosition(Boolean.TRUE.equals(request.getIsManagerPosition()));
    }

    public PositionResponse toResponse(Position entity) {
        return PositionResponse.builder()
                .positionId(entity.getPositionId())
                .companyId(entity.getCompanyId())
                .departmentId(entity.getDepartmentId())
                .jobLevelId(entity.getJobLevelId())
                .jobFamilyId(entity.getJobFamilyId())
                .positionCode(entity.getPositionCode())
                .positionName(entity.getPositionName())
                .positionNameLocal(entity.getPositionNameLocal())
                .positionType(entity.getPositionType())
                .description(entity.getDescription())
                .sortOrder(entity.getSortOrder())
                .isManagerPosition(entity.getIsManagerPosition())
                .isActive(entity.getIsActive())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
