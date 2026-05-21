package com.bakertilly.bt_hrm_api.app.system.menu.repository;

import com.bakertilly.bt_hrm_api.app.system.menu.entity.SysMenuItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SysMenuItemRepository extends JpaRepository<SysMenuItem, String> {
    Page<SysMenuItem> findByDeletedAtIsNull(Pageable pageable);
    Page<SysMenuItem> findByIsVisibleAndDeletedAtIsNull(Boolean isVisible, Pageable pageable);
    Page<SysMenuItem> findByIsActiveAndDeletedAtIsNull(Boolean isActive, Pageable pageable);
    Page<SysMenuItem> findByIsVisibleAndIsActiveAndDeletedAtIsNull(Boolean isVisible, Boolean isActive, Pageable pageable);
    List<SysMenuItem> findByDeletedAtIsNullOrderBySortOrderAscMenuCodeAsc();
    Optional<SysMenuItem> findByMenuItemIdAndDeletedAtIsNull(String menuItemId);
    boolean existsByMenuCodeAndDeletedAtIsNull(String menuCode);
    boolean existsByMenuCodeAndMenuItemIdNotAndDeletedAtIsNull(String menuCode, String menuItemId);
}
