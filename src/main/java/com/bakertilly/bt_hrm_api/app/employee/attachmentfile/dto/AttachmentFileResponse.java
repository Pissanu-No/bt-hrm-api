package com.bakertilly.bt_hrm_api.app.employee.attachmentfile.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@Schema(description = "Employee attachment file metadata response")
public class AttachmentFileResponse {
    private String attachmentFileId;
    private String employeeId;
    private String ownerTable;
    private String ownerId;
    private String attachmentCategory;
    private String attachmentType;
    private String attachmentName;
    private String description;
    private String fileName;
    private String originalFileName;
    private String fileExtension;
    private String mimeType;
    private Long fileSize;
    private String storageProvider;
    private String blobContainerName;
    private String blobName;
    private String filePath;
    private String fileUrl;
    private String checksum;
    private String accessLevel;
    private Boolean isConfidential;
    private LocalDateTime uploadedAt;
    private String uploadedBy;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
