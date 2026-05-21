package com.bakertilly.bt_hrm_api.app.master.lookup.mapper;

import com.bakertilly.bt_hrm_api.app.master.lookup.dto.*;
import com.bakertilly.bt_hrm_api.app.master.lookup.entity.LookupGroup;
import com.bakertilly.bt_hrm_api.app.master.lookup.entity.LookupValue;
import org.springframework.stereotype.Component;

@Component
public class LookupMapper {
    public void updateGroup(LookupGroup entity, LookupGroupRequest request) {
        entity.setLookupGroupCode(request.getLookupGroupCode());
        entity.setLookupGroupName(request.getLookupGroupName());
        entity.setLookupGroupNameLocal(request.getLookupGroupNameLocal());
        entity.setModuleCode(request.getModuleCode());
        entity.setDescription(request.getDescription());
        entity.setIsSystem(Boolean.TRUE.equals(request.getIsSystem()));
        entity.setSortOrder(request.getSortOrder() == null ? 0 : request.getSortOrder());
    }
    public void updateValue(LookupValue entity, LookupValueRequest request) {
        entity.setLookupGroupId(request.getLookupGroupId());
        entity.setLookupCode(request.getLookupCode());
        entity.setLookupName(request.getLookupName());
        entity.setLookupNameLocal(request.getLookupNameLocal());
        entity.setValueText(request.getValueText());
        entity.setValueNumber(request.getValueNumber());
        entity.setColorCode(request.getColorCode());
        entity.setIconName(request.getIconName());
        entity.setDescription(request.getDescription());
        entity.setIsDefault(Boolean.TRUE.equals(request.getIsDefault()));
        entity.setIsSystem(Boolean.TRUE.equals(request.getIsSystem()));
        entity.setSortOrder(request.getSortOrder() == null ? 0 : request.getSortOrder());
    }
    public LookupGroupResponse toGroupResponse(LookupGroup entity) {
        return LookupGroupResponse.builder().lookupGroupId(entity.getLookupGroupId()).lookupGroupCode(entity.getLookupGroupCode())
                .lookupGroupName(entity.getLookupGroupName()).lookupGroupNameLocal(entity.getLookupGroupNameLocal())
                .moduleCode(entity.getModuleCode()).description(entity.getDescription()).isSystem(entity.getIsSystem()).sortOrder(entity.getSortOrder())
                .isActive(entity.getIsActive()).createdAt(entity.getCreatedAt()).updatedAt(entity.getUpdatedAt()).build();
    }
    public LookupValueResponse toValueResponse(LookupValue entity) {
        return LookupValueResponse.builder().lookupValueId(entity.getLookupValueId()).lookupGroupId(entity.getLookupGroupId())
                .lookupCode(entity.getLookupCode()).lookupName(entity.getLookupName()).lookupNameLocal(entity.getLookupNameLocal())
                .valueText(entity.getValueText()).valueNumber(entity.getValueNumber()).colorCode(entity.getColorCode())
                .iconName(entity.getIconName()).description(entity.getDescription()).isDefault(entity.getIsDefault())
                .isSystem(entity.getIsSystem()).sortOrder(entity.getSortOrder())
                .isActive(entity.getIsActive()).createdAt(entity.getCreatedAt()).updatedAt(entity.getUpdatedAt()).build();
    }
}
