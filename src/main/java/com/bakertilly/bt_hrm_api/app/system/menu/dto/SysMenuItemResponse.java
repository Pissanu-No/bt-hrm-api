package com.bakertilly.bt_hrm_api.app.system.menu.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@Schema(description = "System menu item response")
public class SysMenuItemResponse {
    private String menuItemId;
    private String parentMenuItemId;
    private String menuCode;
    private String menuName;
    private String menuNameLocal;
    private String moduleCode;
    private String resourceCode;
    private String iconName;
    private String routePath;
    private String permissionCode;
    private Integer sortOrder;
    private Boolean isVisible;
    private Boolean isExternal;
    private String externalUrl;
    private String description;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
