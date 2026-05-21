package com.bakertilly.bt_hrm_api.app.system.menu.service.impl;

import com.bakertilly.bt_hrm_api.app.common.exception.DuplicateResourceException;
import com.bakertilly.bt_hrm_api.app.common.exception.ResourceNotFoundException;
import com.bakertilly.bt_hrm_api.app.common.response.PagedResponse;
import com.bakertilly.bt_hrm_api.app.system.menu.dto.SysMenuItemRequest;
import com.bakertilly.bt_hrm_api.app.system.menu.dto.SysMenuItemResponse;
import com.bakertilly.bt_hrm_api.app.system.menu.dto.SysMenuItemTreeResponse;
import com.bakertilly.bt_hrm_api.app.system.menu.entity.SysMenuItem;
import com.bakertilly.bt_hrm_api.app.system.menu.mapper.SysMenuItemMapper;
import com.bakertilly.bt_hrm_api.app.system.menu.repository.SysMenuItemRepository;
import com.bakertilly.bt_hrm_api.app.system.menu.service.SysMenuItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
@RequiredArgsConstructor
public class SysMenuItemServiceImpl implements SysMenuItemService {
    private final SysMenuItemRepository repository;
    private final SysMenuItemMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public PagedResponse<SysMenuItemResponse> findAll(Boolean isVisible, Boolean isActive, Pageable pageable) {
        Page<SysMenuItem> page;
        if (isVisible != null && isActive != null) {
            page = repository.findByIsVisibleAndIsActiveAndDeletedAtIsNull(isVisible, isActive, pageable);
        } else if (isVisible != null) {
            page = repository.findByIsVisibleAndDeletedAtIsNull(isVisible, pageable);
        } else if (isActive != null) {
            page = repository.findByIsActiveAndDeletedAtIsNull(isActive, pageable);
        } else {
            page = repository.findByDeletedAtIsNull(pageable);
        }
        return PagedResponse.from(page.map(mapper::toResponse));
    }

    @Override
    @Transactional(readOnly = true)
    public List<SysMenuItemTreeResponse> findTree() {
        List<SysMenuItem> items = repository.findByDeletedAtIsNullOrderBySortOrderAscMenuCodeAsc();
        Map<String, List<SysMenuItem>> byParent = new LinkedHashMap<>();
        for (SysMenuItem item : items) {
            byParent.computeIfAbsent(item.getParentMenuItemId(), key -> new ArrayList<>()).add(item);
        }
        return buildTree(null, byParent);
    }

    @Override
    @Transactional(readOnly = true)
    public SysMenuItemResponse findById(String menuItemId) {
        return mapper.toResponse(findActive(menuItemId));
    }

    @Override
    @Transactional
    public SysMenuItemResponse create(SysMenuItemRequest request) {
        validateParent(request.getParentMenuItemId(), null);
        if (repository.existsByMenuCodeAndDeletedAtIsNull(request.getMenuCode())) {
            throw new DuplicateResourceException("Menu code already exists");
        }
        SysMenuItem entity = new SysMenuItem();
        entity.setMenuItemId(UUID.randomUUID().toString());
        mapper.updateEntity(entity, request);
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    @Transactional
    public SysMenuItemResponse update(String menuItemId, SysMenuItemRequest request) {
        SysMenuItem entity = findActive(menuItemId);
        validateParent(request.getParentMenuItemId(), menuItemId);
        if (repository.existsByMenuCodeAndMenuItemIdNotAndDeletedAtIsNull(request.getMenuCode(), menuItemId)) {
            throw new DuplicateResourceException("Menu code already exists");
        }
        mapper.updateEntity(entity, request);
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    @Transactional
    public void delete(String menuItemId) {
        SysMenuItem entity = findActive(menuItemId);
        entity.softDelete();
        repository.save(entity);
    }

    private List<SysMenuItemTreeResponse> buildTree(String parentId, Map<String, List<SysMenuItem>> byParent) {
        return byParent.getOrDefault(parentId, List.of()).stream()
                .map(item -> mapper.toTree(item, buildTree(item.getMenuItemId(), byParent)))
                .toList();
    }

    private SysMenuItem findActive(String menuItemId) {
        return repository.findByMenuItemIdAndDeletedAtIsNull(menuItemId)
                .orElseThrow(() -> new ResourceNotFoundException("Menu item not found"));
    }

    private void validateParent(String parentMenuItemId, String currentMenuItemId) {
        if (!StringUtils.hasText(parentMenuItemId)) {
            return;
        }
        if (parentMenuItemId.equals(currentMenuItemId)) {
            throw new IllegalArgumentException("Menu item cannot be its own parent");
        }
        findActive(parentMenuItemId);
    }
}
