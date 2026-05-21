package com.bakertilly.bt_hrm_api.app.position.controller;

import com.bakertilly.bt_hrm_api.app.common.response.ApiResponse;
import com.bakertilly.bt_hrm_api.app.common.response.PagedResponse;
import com.bakertilly.bt_hrm_api.app.position.dto.PositionRequest;
import com.bakertilly.bt_hrm_api.app.position.dto.PositionResponse;
import com.bakertilly.bt_hrm_api.app.position.service.PositionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/s/positions")
@Tag(name = "position", description = "Position master data APIs")
public class PositionController {
    private final PositionService positionService;

    @GetMapping
    @Operation(summary = "List positions")
    @io.swagger.v3.oas.annotations.responses.ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Positions retrieved")
    })
    public ApiResponse<PagedResponse<PositionResponse>> findAll(
            @ParameterObject @PageableDefault(size = 10, sort = "createdAt") Pageable pageable) {
        return ApiResponse.success(positionService.findAll(pageable));
    }

    @GetMapping("/{positionId}")
    @Operation(summary = "Get position by ID")
    @io.swagger.v3.oas.annotations.responses.ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Position retrieved"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Position not found")
    })
    public ApiResponse<PositionResponse> findById(
            @Parameter(description = "Position ID") @PathVariable String positionId) {
        return ApiResponse.success(positionService.findById(positionId));
    }

    @PostMapping
    @Operation(summary = "Create position")
    @io.swagger.v3.oas.annotations.responses.ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Position created"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Validation failed"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "409", description = "Position code already exists")
    })
    public ResponseEntity<ApiResponse<PositionResponse>> create(@Valid @RequestBody PositionRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(positionService.create(request)));
    }

    @PutMapping("/{positionId}")
    @Operation(summary = "Update position")
    public ApiResponse<PositionResponse> update(
            @Parameter(description = "Position ID") @PathVariable String positionId,
            @Valid @RequestBody PositionRequest request) {
        return ApiResponse.success(positionService.update(positionId, request));
    }

    @DeleteMapping("/{positionId}")
    @Operation(summary = "Soft delete position")
    public ApiResponse<Void> delete(@Parameter(description = "Position ID") @PathVariable String positionId) {
        positionService.delete(positionId);
        return ApiResponse.success(null);
    }
}
