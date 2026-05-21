package com.bakertilly.bt_hrm_api.app.employee.attachmentfile.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(description = "Employee attachment file metadata create/update request")
public class AttachmentFileRequest {
    @Size(max = 100)
    private String ownerTable;

    @Size(max = 60)
    private String ownerId;

    @NotBlank(message = "Attachment category is required")
    @Size(max = 100)
    private String attachmentCategory;

    @NotBlank(message = "Attachment type is required")
    @Size(max = 100)
    private String attachmentType;

    @NotBlank(message = "Attachment name is required")
    @Size(max = 255)
    private String attachmentName;

    @Size(max = 1000)
    private String description;

    @NotBlank(message = "File name is required")
    @Size(max = 255)
    private String fileName;

    @Size(max = 255)
    private String originalFileName;

    @Size(max = 50)
    private String fileExtension;

    @Size(max = 100)
    private String mimeType;

    private Long fileSize;

    @Size(max = 50)
    private String storageProvider;

    @Size(max = 255)
    private String blobContainerName;

    @Size(max = 1000)
    private String blobName;

    @Size(max = 1000)
    private String filePath;

    @Size(max = 1000)
    private String fileUrl;

    @Size(max = 255)
    private String checksum;

    @Size(max = 50)
    private String accessLevel;

    private Boolean isConfidential;

    private LocalDateTime uploadedAt;

    @Size(max = 100)
    private String uploadedBy;
}
