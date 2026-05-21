package com.bakertilly.bt_hrm_api.app.employee.attachmentfile.mapper;

import com.bakertilly.bt_hrm_api.app.employee.attachmentfile.dto.AttachmentFileRequest;
import com.bakertilly.bt_hrm_api.app.employee.attachmentfile.dto.AttachmentFileResponse;
import com.bakertilly.bt_hrm_api.app.employee.attachmentfile.entity.AttachmentFile;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

@Component
public class AttachmentFileMapper {
    public void updateEntity(AttachmentFile entity, AttachmentFileRequest request) {
        entity.setOwnerTable(request.getOwnerTable());
        entity.setOwnerId(request.getOwnerId());
        entity.setAttachmentCategory(request.getAttachmentCategory());
        entity.setAttachmentType(request.getAttachmentType());
        entity.setAttachmentName(request.getAttachmentName());
        entity.setDescription(request.getDescription());
        entity.setFileName(request.getFileName());
        entity.setOriginalFileName(request.getOriginalFileName());
        entity.setFileExtension(request.getFileExtension());
        entity.setMimeType(request.getMimeType());
        entity.setFileSize(request.getFileSize());
        entity.setStorageProvider(StringUtils.hasText(request.getStorageProvider()) ? request.getStorageProvider() : "AZURE_BLOB");
        entity.setBlobContainerName(request.getBlobContainerName());
        entity.setBlobName(request.getBlobName());
        entity.setFilePath(request.getFilePath());
        entity.setFileUrl(request.getFileUrl());
        entity.setChecksum(request.getChecksum());
        entity.setAccessLevel(StringUtils.hasText(request.getAccessLevel()) ? request.getAccessLevel() : "HR_CONFIDENTIAL");
        entity.setIsConfidential(request.getIsConfidential() == null || request.getIsConfidential());
        entity.setUploadedAt(request.getUploadedAt() == null ? LocalDateTime.now() : request.getUploadedAt());
        entity.setUploadedBy(request.getUploadedBy());
    }

    public AttachmentFileResponse toResponse(AttachmentFile entity) {
        return AttachmentFileResponse.builder()
                .attachmentFileId(entity.getAttachmentFileId())
                .employeeId(entity.getEmployeeId())
                .ownerTable(entity.getOwnerTable())
                .ownerId(entity.getOwnerId())
                .attachmentCategory(entity.getAttachmentCategory())
                .attachmentType(entity.getAttachmentType())
                .attachmentName(entity.getAttachmentName())
                .description(entity.getDescription())
                .fileName(entity.getFileName())
                .originalFileName(entity.getOriginalFileName())
                .fileExtension(entity.getFileExtension())
                .mimeType(entity.getMimeType())
                .fileSize(entity.getFileSize())
                .storageProvider(entity.getStorageProvider())
                .blobContainerName(entity.getBlobContainerName())
                .blobName(entity.getBlobName())
                .filePath(entity.getFilePath())
                .fileUrl(entity.getFileUrl())
                .checksum(entity.getChecksum())
                .accessLevel(entity.getAccessLevel())
                .isConfidential(entity.getIsConfidential())
                .uploadedAt(entity.getUploadedAt())
                .uploadedBy(entity.getUploadedBy())
                .isActive(entity.getIsActive())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
