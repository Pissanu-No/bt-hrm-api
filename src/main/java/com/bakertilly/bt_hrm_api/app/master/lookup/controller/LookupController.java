package com.bakertilly.bt_hrm_api.app.master.lookup.controller;

import com.bakertilly.bt_hrm_api.app.common.response.ApiResponse;
import com.bakertilly.bt_hrm_api.app.common.response.PagedResponse;
import com.bakertilly.bt_hrm_api.app.master.lookup.dto.*;
import com.bakertilly.bt_hrm_api.app.master.lookup.service.LookupService;
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
@Tag(name = "Lookup Master", description = "Lookup group and lookup value APIs")
public class LookupController {
    private final LookupService service;
    @GetMapping("/v1/s/master/lookup-groups") @Operation(summary = "List lookup groups")
    public ApiResponse<PagedResponse<LookupGroupResponse>> findGroups(@ParameterObject @PageableDefault(size = 10, sort = "createdAt") Pageable pageable) { return ApiResponse.success(service.findGroups(pageable)); }
    @GetMapping("/v1/s/master/lookup-groups/{lookupGroupId}") @Operation(summary = "Get lookup group by ID")
    public ApiResponse<LookupGroupResponse> findGroupById(@PathVariable String lookupGroupId) { return ApiResponse.success(service.findGroupById(lookupGroupId)); }
    @PostMapping("/v1/s/master/lookup-groups") @Operation(summary = "Create lookup group")
    public ResponseEntity<ApiResponse<LookupGroupResponse>> createGroup(@Valid @RequestBody LookupGroupRequest request) { return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(service.createGroup(request))); }
    @PutMapping("/v1/s/master/lookup-groups/{lookupGroupId}") @Operation(summary = "Update lookup group")
    public ApiResponse<LookupGroupResponse> updateGroup(@PathVariable String lookupGroupId, @Valid @RequestBody LookupGroupRequest request) { return ApiResponse.success(service.updateGroup(lookupGroupId, request)); }
    @DeleteMapping("/v1/s/master/lookup-groups/{lookupGroupId}") @Operation(summary = "Soft delete lookup group")
    public ApiResponse<Void> deleteGroup(@PathVariable String lookupGroupId) { service.deleteGroup(lookupGroupId); return ApiResponse.success(null); }
    @GetMapping("/v1/s/master/lookup-values") @Operation(summary = "List lookup values")
    public ApiResponse<PagedResponse<LookupValueResponse>> findValues(@ParameterObject @PageableDefault(size = 10, sort = "createdAt") Pageable pageable) { return ApiResponse.success(service.findValues(pageable)); }
    @GetMapping("/v1/s/master/lookup-values/{lookupValueId}") @Operation(summary = "Get lookup value by ID")
    public ApiResponse<LookupValueResponse> findValueById(@PathVariable String lookupValueId) { return ApiResponse.success(service.findValueById(lookupValueId)); }
    @GetMapping("/v1/s/master/lookup-groups/{lookupGroupCode}/values") @Operation(summary = "List lookup values by lookup group code")
    public ApiResponse<List<LookupValueResponse>> findValuesByGroupCode(@PathVariable String lookupGroupCode) { return ApiResponse.success(service.findValuesByGroupCode(lookupGroupCode)); }
    @PostMapping("/v1/s/master/lookup-values") @Operation(summary = "Create lookup value")
    public ResponseEntity<ApiResponse<LookupValueResponse>> createValue(@Valid @RequestBody LookupValueRequest request) { return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(service.createValue(request))); }
    @PutMapping("/v1/s/master/lookup-values/{lookupValueId}") @Operation(summary = "Update lookup value")
    public ApiResponse<LookupValueResponse> updateValue(@PathVariable String lookupValueId, @Valid @RequestBody LookupValueRequest request) { return ApiResponse.success(service.updateValue(lookupValueId, request)); }
    @DeleteMapping("/v1/s/master/lookup-values/{lookupValueId}") @Operation(summary = "Soft delete lookup value")
    public ApiResponse<Void> deleteValue(@PathVariable String lookupValueId) { service.deleteValue(lookupValueId); return ApiResponse.success(null); }
}
