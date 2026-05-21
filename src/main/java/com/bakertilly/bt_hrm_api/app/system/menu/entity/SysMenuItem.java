package com.bakertilly.bt_hrm_api.app.system.menu.entity;

import com.bakertilly.bt_hrm_api.app.common.persistence.AuditableSoftDeleteEntity;
import com.bakertilly.bt_hrm_api.app.common.persistence.UuidPrimaryKeyEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "hris_master_menu_item", schema = "hris")
public class SysMenuItem extends AuditableSoftDeleteEntity implements UuidPrimaryKeyEntity {
    @Id
    @Column(name = "menu_item_id", length = 60, nullable = false)
    private String menuItemId;
    @Column(name = "parent_menu_item_id", length = 60)
    private String parentMenuItemId;
    @Column(name = "menu_code", length = 100, nullable = false)
    private String menuCode;
    @Column(name = "menu_name", nullable = false)
    private String menuName;
    @Column(name = "menu_name_local")
    private String menuNameLocal;
    @Column(name = "module_code", length = 100, nullable = false)
    private String moduleCode;
    @Column(name = "resource_code", length = 100)
    private String resourceCode;
    @Column(name = "icon_name", length = 100)
    private String iconName;
    @Column(name = "route_path", length = 500)
    private String routePath;
    @Column(name = "permission_code", length = 150)
    private String permissionCode;
    @Column(name = "sort_order", nullable = false)
    private Integer sortOrder = 0;
    @Column(name = "is_visible", nullable = false)
    private Boolean isVisible = true;
    @Column(name = "is_external", nullable = false)
    private Boolean isExternal = false;
    @Column(name = "external_url", length = 1000)
    private String externalUrl;
    @Column(name = "description", length = 1000)
    private String description;
    @Override public String getPrimaryKeyValue() { return menuItemId; }
    @Override public void setPrimaryKeyValue(String primaryKeyValue) { menuItemId = primaryKeyValue; }
    @Override public String getPrimaryKeyFieldName() { return "menuItemId"; }
}
