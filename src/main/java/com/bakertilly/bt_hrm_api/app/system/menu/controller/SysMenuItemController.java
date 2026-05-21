package com.bakertilly.bt_hrm_api.app.system.menu.controller;

import com.bakertilly.bt_hrm_api.app.common.response.ApiResponse;
import com.bakertilly.bt_hrm_api.app.common.response.PagedResponse;
import com.bakertilly.bt_hrm_api.app.system.menu.dto.SysMenuItemRequest;
import com.bakertilly.bt_hrm_api.app.system.menu.dto.SysMenuItemResponse;
import com.bakertilly.bt_hrm_api.app.system.menu.dto.SysMenuItemTreeResponse;
import com.bakertilly.bt_hrm_api.app.system.menu.service.SysMenuItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/s/system/menu-items")
@Tag(name = "System Menu", description = "System menu item APIs")
public class SysMenuItemController {
    private final SysMenuItemService service;

    @GetMapping
    @Operation(summary = "List system menu items")
    public ApiResponse<PagedResponse<SysMenuItemResponse>> findAll(
            @RequestParam(required = false) Boolean isVisible,
            @RequestParam(required = false) Boolean isActive,
            @ParameterObject @PageableDefault(size = 10, sort = "sortOrder") Pageable pageable) {
        return ApiResponse.success(service.findAll(isVisible, isActive, pageable));
    }

    @GetMapping("/tree")
    @Operation(summary = "Get system menu tree")
    public ApiResponse<List<SysMenuItemTreeResponse>> findTree() {
        return ApiResponse.success(service.findTree());
    }

    @GetMapping("/{menuItemId}")
    @Operation(summary = "Get system menu item by ID")
    public ApiResponse<SysMenuItemResponse> findById(@PathVariable String menuItemId) {
        return ApiResponse.success(service.findById(menuItemId));
    }

    @PostMapping
    @Operation(summary = "Create system menu item")
    public ResponseEntity<ApiResponse<SysMenuItemResponse>> create(@Valid @RequestBody SysMenuItemRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(service.create(request)));
    }

    @PutMapping("/{menuItemId}")
    @Operation(summary = "Update system menu item")
    public ApiResponse<SysMenuItemResponse> update(@PathVariable String menuItemId,
                                                   @Valid @RequestBody SysMenuItemRequest request) {
        return ApiResponse.success(service.update(menuItemId, request));
    }

    @DeleteMapping("/{menuItemId}")
    @Operation(summary = "Soft delete system menu item")
    public ApiResponse<Void> delete(@PathVariable String menuItemId) {
        service.delete(menuItemId);
        return ApiResponse.success(null);
    }
}
