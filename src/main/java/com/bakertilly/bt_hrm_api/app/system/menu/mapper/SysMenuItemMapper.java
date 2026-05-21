package com.bakertilly.bt_hrm_api.app.system.menu.mapper;

import com.bakertilly.bt_hrm_api.app.system.menu.dto.SysMenuItemRequest;
import com.bakertilly.bt_hrm_api.app.system.menu.dto.SysMenuItemResponse;
import com.bakertilly.bt_hrm_api.app.system.menu.dto.SysMenuItemTreeResponse;
import com.bakertilly.bt_hrm_api.app.system.menu.entity.SysMenuItem;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SysMenuItemMapper {
    public void updateEntity(SysMenuItem entity, SysMenuItemRequest request) {
        entity.setParentMenuItemId(request.getParentMenuItemId());
        entity.setMenuCode(request.getMenuCode());
        entity.setMenuName(request.getMenuName());
        entity.setMenuNameLocal(request.getMenuNameLocal());
        entity.setModuleCode(request.getModuleCode());
        entity.setResourceCode(request.getResourceCode());
        entity.setIconName(request.getIconName());
        entity.setRoutePath(request.getRoutePath());
        entity.setPermissionCode(request.getPermissionCode());
        entity.setSortOrder(request.getSortOrder() == null ? 0 : request.getSortOrder());
        entity.setIsVisible(request.getIsVisible() == null || request.getIsVisible());
        entity.setIsExternal(Boolean.TRUE.equals(request.getIsExternal()));
        entity.setExternalUrl(request.getExternalUrl());
        entity.setDescription(request.getDescription());
    }
    public SysMenuItemResponse toResponse(SysMenuItem entity) {
        return SysMenuItemResponse.builder().menuItemId(entity.getMenuItemId()).parentMenuItemId(entity.getParentMenuItemId())
                .menuCode(entity.getMenuCode()).menuName(entity.getMenuName()).menuNameLocal(entity.getMenuNameLocal())
                .moduleCode(entity.getModuleCode()).resourceCode(entity.getResourceCode()).iconName(entity.getIconName())
                .routePath(entity.getRoutePath())
                .permissionCode(entity.getPermissionCode()).sortOrder(entity.getSortOrder()).isVisible(entity.getIsVisible())
                .isExternal(entity.getIsExternal()).externalUrl(entity.getExternalUrl()).description(entity.getDescription())
                .isActive(entity.getIsActive()).createdAt(entity.getCreatedAt()).updatedAt(entity.getUpdatedAt()).build();
    }
    public SysMenuItemTreeResponse toTree(SysMenuItem entity, List<SysMenuItemTreeResponse> children) {
        return SysMenuItemTreeResponse.builder().menuItemId(entity.getMenuItemId()).parentMenuItemId(entity.getParentMenuItemId())
                .menuCode(entity.getMenuCode()).menuName(entity.getMenuName()).menuNameLocal(entity.getMenuNameLocal())
                .moduleCode(entity.getModuleCode()).resourceCode(entity.getResourceCode()).iconName(entity.getIconName())
                .routePath(entity.getRoutePath())
                .permissionCode(entity.getPermissionCode()).sortOrder(entity.getSortOrder()).isVisible(entity.getIsVisible())
                .isExternal(entity.getIsExternal()).externalUrl(entity.getExternalUrl()).description(entity.getDescription())
                .children(children).build();
    }
}
