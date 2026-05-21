package com.bakertilly.bt_hrm_api.app.employee.attachmentfile.controller;

import com.bakertilly.bt_hrm_api.app.common.response.ApiResponse;
import com.bakertilly.bt_hrm_api.app.common.response.PagedResponse;
import com.bakertilly.bt_hrm_api.app.employee.attachmentfile.dto.AttachmentFileRequest;
import com.bakertilly.bt_hrm_api.app.employee.attachmentfile.dto.AttachmentFileResponse;
import com.bakertilly.bt_hrm_api.app.employee.attachmentfile.dto.AttachmentFileUploadRequest;
import com.bakertilly.bt_hrm_api.app.employee.attachmentfile.dto.StorageFileDownload;
import com.bakertilly.bt_hrm_api.app.employee.attachmentfile.service.AttachmentFileService;
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
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.StandardCharsets;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/s/employees/{employeeId}/attachment-files")
@Tag(name = "Employee Attachment File", description = "Employee attachment file APIs")
public class AttachmentFileController {
    private final AttachmentFileService service;

    @GetMapping
    @Operation(summary = "List employee attachment files")
    public ApiResponse<PagedResponse<AttachmentFileResponse>> findAll(
            @PathVariable String employeeId,
            @ParameterObject @PageableDefault(size = 10, sort = "createdAt") Pageable pageable) {
        return ApiResponse.success(service.findAll(employeeId, pageable));
    }

    @GetMapping("/{attachmentFileId}")
    @Operation(summary = "Get employee attachment file by ID")
    public ApiResponse<AttachmentFileResponse> findById(@PathVariable String employeeId,
                                                        @PathVariable String attachmentFileId) {
        return ApiResponse.success(service.findById(employeeId, attachmentFileId));
    }

    @PostMapping
    @Operation(summary = "Create employee attachment file metadata")
    public ResponseEntity<ApiResponse<AttachmentFileResponse>> create(
            @PathVariable String employeeId,
            @Valid @RequestBody AttachmentFileRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(service.create(employeeId, request)));
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(
            summary = "Upload employee attachment file",
            description = "Uploads employee document to Azure Blob Storage and stores attachment metadata",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(
                    mediaType = MediaType.MULTIPART_FORM_DATA_VALUE,
                    schema = @Schema(implementation = AttachmentFileUploadRequest.class)
            ))
    )
    public ResponseEntity<ApiResponse<AttachmentFileResponse>> upload(
            @PathVariable String employeeId,
            @Parameter(description = "Binary file to upload", required = true)
            @RequestPart("file") MultipartFile file,
            @Valid @ModelAttribute AttachmentFileUploadRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(service.upload(employeeId, file, request)));
    }

    @GetMapping("/{attachmentFileId}/download")
    @Operation(summary = "Download employee attachment file")
    public ResponseEntity<byte[]> download(@PathVariable String employeeId,
                                           @PathVariable String attachmentFileId) {
        StorageFileDownload download = service.download(employeeId, attachmentFileId);
        MediaType contentType = MediaType.parseMediaType(StringUtils.hasText(download.getContentType())
                ? download.getContentType()
                : MediaType.APPLICATION_OCTET_STREAM_VALUE);

        return ResponseEntity.ok()
                .contentType(contentType)
                .contentLength(download.getContentLength())
                .header(HttpHeaders.CONTENT_DISPOSITION, ContentDisposition.attachment()
                        .filename(download.getFileName(), StandardCharsets.UTF_8)
                        .build()
                        .toString())
                .body(download.getContent());
    }

    @PutMapping("/{attachmentFileId}")
    @Operation(summary = "Update employee attachment file metadata")
    public ApiResponse<AttachmentFileResponse> update(@PathVariable String employeeId,
                                                      @PathVariable String attachmentFileId,
                                                      @Valid @RequestBody AttachmentFileRequest request) {
        return ApiResponse.success(service.update(employeeId, attachmentFileId, request));
    }

    @DeleteMapping("/{attachmentFileId}")
    @Operation(summary = "Soft delete employee attachment file metadata")
    public ApiResponse<Void> delete(@PathVariable String employeeId, @PathVariable String attachmentFileId) {
        service.delete(employeeId, attachmentFileId);
        return ApiResponse.success(null);
    }
}
