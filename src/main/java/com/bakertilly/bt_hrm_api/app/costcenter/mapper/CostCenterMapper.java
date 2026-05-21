package com.bakertilly.bt_hrm_api.app.costcenter.mapper;

import com.bakertilly.bt_hrm_api.app.costcenter.dto.CostCenterRequest;
import com.bakertilly.bt_hrm_api.app.costcenter.dto.CostCenterResponse;
import com.bakertilly.bt_hrm_api.app.costcenter.entity.CostCenter;
import org.springframework.stereotype.Component;

@Component
public class CostCenterMapper {
    public void updateEntity(CostCenter entity, CostCenterRequest request) {
        entity.setCompanyId(request.getCompanyId());
        entity.setCostCenterCode(request.getCostCenterCode());
        entity.setCostCenterName(request.getCostCenterName());
        entity.setCostCenterNameLocal(request.getCostCenterNameLocal());
        entity.setCostCenterType(request.getCostCenterType());
        entity.setSortOrder(request.getSortOrder() == null ? 0 : request.getSortOrder());
        entity.setDescription(request.getDescription());
    }
    public CostCenterResponse toResponse(CostCenter entity) {
        return CostCenterResponse.builder().costCenterId(entity.getCostCenterId()).companyId(entity.getCompanyId())
                .costCenterCode(entity.getCostCenterCode()).costCenterName(entity.getCostCenterName())
                .costCenterNameLocal(entity.getCostCenterNameLocal()).costCenterType(entity.getCostCenterType())
                .sortOrder(entity.getSortOrder()).description(entity.getDescription()).isActive(entity.getIsActive())
                .createdAt(entity.getCreatedAt()).updatedAt(entity.getUpdatedAt()).build();
    }
}
