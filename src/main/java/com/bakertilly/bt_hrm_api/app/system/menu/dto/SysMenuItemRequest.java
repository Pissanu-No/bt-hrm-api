package com.bakertilly.bt_hrm_api.app.system.menu.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "System menu item create/update request")
public class SysMenuItemRequest {
    @Size(max = 60)
    private String parentMenuItemId;
    @NotBlank(message = "Menu code is required") @Size(max = 100)
    private String menuCode;
    @NotBlank(message = "Menu name is required") @Size(max = 255)
    private String menuName;
    @Size(max = 255)
    private String menuNameLocal;
    @NotBlank(message = "Module code is required") @Size(max = 100)
    private String moduleCode;
    @Size(max = 100)
    private String resourceCode;
    @Size(max = 100)
    private String iconName;
    @Size(max = 500)
    private String routePath;
    @Size(max = 150)
    private String permissionCode;
    private Integer sortOrder;
    private Boolean isVisible;
    private Boolean isExternal;
    @Size(max = 1000)
    private String externalUrl;
    @Size(max = 1000)
    private String description;
}
