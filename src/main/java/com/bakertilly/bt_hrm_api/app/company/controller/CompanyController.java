package com.bakertilly.bt_hrm_api.app.company.controller;

import com.bakertilly.bt_hrm_api.app.common.response.ApiResponse;
import com.bakertilly.bt_hrm_api.app.common.response.PagedResponse;
import com.bakertilly.bt_hrm_api.app.company.dto.CompanyRequest;
import com.bakertilly.bt_hrm_api.app.company.dto.CompanyResponse;
import com.bakertilly.bt_hrm_api.app.company.service.CompanyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
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
@RequestMapping("/v1/s/companies")
@Tag(name = "company", description = "Company master data APIs")
public class CompanyController {
    private final CompanyService companyService;

    @GetMapping
    @Operation(summary = "List companies")
    @io.swagger.v3.oas.annotations.responses.ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Companies retrieved")
    })
    public ApiResponse<PagedResponse<CompanyResponse>> findAll(
            @ParameterObject @PageableDefault(size = 10, sort = "createdAt") Pageable pageable) {
        return ApiResponse.success(companyService.findAll(pageable));
    }

    @GetMapping("/{companyId}")
    @Operation(summary = "Get company by ID")
    @io.swagger.v3.oas.annotations.responses.ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Company retrieved",
                    content = @Content(schema = @Schema(implementation = CompanyResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Company not found")
    })
    public ApiResponse<CompanyResponse> findById(
            @Parameter(description = "Company ID") @PathVariable String companyId) {
        return ApiResponse.success(companyService.findById(companyId));
    }

    @PostMapping
    @Operation(summary = "Create company")
    @io.swagger.v3.oas.annotations.responses.ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Company created"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Validation failed"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "409", description = "Company code already exists")
    })
    public ResponseEntity<ApiResponse<CompanyResponse>> create(@Valid @RequestBody CompanyRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(companyService.create(request)));
    }

    @PutMapping("/{companyId}")
    @Operation(summary = "Update company")
    public ApiResponse<CompanyResponse> update(
            @Parameter(description = "Company ID") @PathVariable String companyId,
            @Valid @RequestBody CompanyRequest request) {
        return ApiResponse.success(companyService.update(companyId, request));
    }

    @DeleteMapping("/{companyId}")
    @Operation(summary = "Soft delete company")
    public ApiResponse<Void> delete(@Parameter(description = "Company ID") @PathVariable String companyId) {
        companyService.delete(companyId);
        return ApiResponse.success(null);
    }
}
