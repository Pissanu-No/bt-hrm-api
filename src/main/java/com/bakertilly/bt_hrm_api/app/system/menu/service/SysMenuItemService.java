package com.bakertilly.bt_hrm_api.app.system.menu.service;

import com.bakertilly.bt_hrm_api.app.common.response.PagedResponse;
import com.bakertilly.bt_hrm_api.app.system.menu.dto.SysMenuItemRequest;
import com.bakertilly.bt_hrm_api.app.system.menu.dto.SysMenuItemResponse;
import com.bakertilly.bt_hrm_api.app.system.menu.dto.SysMenuItemTreeResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SysMenuItemService {
    PagedResponse<SysMenuItemResponse> findAll(Boolean isVisible, Boolean isActive, Pageable pageable);
    List<SysMenuItemTreeResponse> findTree();
    SysMenuItemResponse findById(String menuItemId);
    SysMenuItemResponse create(SysMenuItemRequest request);
    SysMenuItemResponse update(String menuItemId, SysMenuItemRequest request);
    void delete(String menuItemId);
}
