package com.bakertilly.bt_hrm_api.app.employee.attachmentfile.service.impl;

import com.bakertilly.bt_hrm_api.app.common.exception.ResourceNotFoundException;
import com.bakertilly.bt_hrm_api.app.common.response.PagedResponse;
import com.bakertilly.bt_hrm_api.app.employee.attachmentfile.dto.AttachmentFileRequest;
import com.bakertilly.bt_hrm_api.app.employee.attachmentfile.dto.AttachmentFileResponse;
import com.bakertilly.bt_hrm_api.app.employee.attachmentfile.dto.AttachmentFileUploadRequest;
import com.bakertilly.bt_hrm_api.app.employee.attachmentfile.dto.StorageFileDownload;
import com.bakertilly.bt_hrm_api.app.employee.attachmentfile.entity.AttachmentFile;
import com.bakertilly.bt_hrm_api.app.employee.attachmentfile.mapper.AttachmentFileMapper;
import com.bakertilly.bt_hrm_api.app.employee.attachmentfile.repository.AttachmentFileRepository;
import com.bakertilly.bt_hrm_api.app.employee.attachmentfile.service.AttachmentFileService;
import com.bakertilly.bt_hrm_api.app.employee.attachmentfile.storage.BlobAttachmentFileStorageClient;
import com.bakertilly.bt_hrm_api.app.employee.attachmentfile.storage.BlobStorageFileUploadResult;
import com.bakertilly.bt_hrm_api.app.employee.repository.EmployeeRepository;
import com.bakertilly.bt_hrm_api.core.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AttachmentFileServiceImpl implements AttachmentFileService {
    private final AttachmentFileRepository repository;
    private final EmployeeRepository employeeRepository;
    private final AttachmentFileMapper mapper;
    private final BlobAttachmentFileStorageClient fileStorageClient;

    @Override
    @Transactional(readOnly = true)
    public PagedResponse<AttachmentFileResponse> findAll(String employeeId, Pageable pageable) {
        validateEmployee(employeeId);
        return PagedResponse.from(repository.findByEmployeeIdAndIsActiveTrueAndDeletedAtIsNull(employeeId, pageable)
                .map(mapper::toResponse));
    }

    @Override
    @Transactional(readOnly = true)
    public AttachmentFileResponse findById(String employeeId, String attachmentFileId) {
        return mapper.toResponse(findActive(employeeId, attachmentFileId));
    }

    @Override
    @Transactional
    public AttachmentFileResponse create(String employeeId, AttachmentFileRequest request) {
        validateEmployee(employeeId);
        AttachmentFile entity = new AttachmentFile();
        entity.setAttachmentFileId(UUID.randomUUID().toString());
        entity.setEmployeeId(employeeId);
        mapper.updateEntity(entity, request);
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    @Transactional
    public AttachmentFileResponse upload(String employeeId, MultipartFile file, AttachmentFileUploadRequest request) {
        validateEmployee(employeeId);
        if (file == null || file.isEmpty()) {
            throw new ServiceException(HttpStatus.BAD_REQUEST, "UPLOAD_FILE_REQUIRED", null,
                    "Upload file is required");
        }

        BlobStorageFileUploadResult uploadResult = fileStorageClient.upload(file, "Profile/" + employeeId);
        AttachmentFile entity = new AttachmentFile();
        entity.setAttachmentFileId(UUID.randomUUID().toString());
        entity.setEmployeeId(employeeId);
        entity.setOwnerTable(request.getOwnerTable());
        entity.setOwnerId(request.getOwnerId());
        entity.setAttachmentCategory(request.getAttachmentCategory());
        entity.setAttachmentType(request.getAttachmentType());
        entity.setAttachmentName(StringUtils.hasText(request.getAttachmentName())
                ? request.getAttachmentName()
                : uploadResult.getOriginalFileName());
        entity.setDescription(request.getDescription());
        entity.setFileName(uploadResult.getFileName());
        entity.setOriginalFileName(uploadResult.getOriginalFileName());
        entity.setFileExtension(uploadResult.getFileExtension());
        entity.setMimeType(uploadResult.getMimeType());
        entity.setFileSize(uploadResult.getFileSize());
        entity.setStorageProvider("AZURE_BLOB");
        entity.setBlobContainerName(uploadResult.getBlobContainerName());
        entity.setBlobName(uploadResult.getBlobName());
        entity.setFilePath(uploadResult.getFilePath());
        entity.setFileUrl(uploadResult.getFileUrl());
        entity.setChecksum(uploadResult.getChecksum());
        entity.setAccessLevel(StringUtils.hasText(request.getAccessLevel()) ? request.getAccessLevel() : "HR_CONFIDENTIAL");
        entity.setIsConfidential(request.getIsConfidential() == null || request.getIsConfidential());
        entity.setUploadedAt(LocalDateTime.now());
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public StorageFileDownload download(String employeeId, String attachmentFileId) {
        AttachmentFile entity = findActive(employeeId, attachmentFileId);
        return fileStorageClient.download(entity.getBlobName(), entity.getOriginalFileName(), entity.getMimeType());
    }

    @Override
    @Transactional
    public AttachmentFileResponse update(String employeeId, String attachmentFileId, AttachmentFileRequest request) {
        AttachmentFile entity = findActive(employeeId, attachmentFileId);
        mapper.updateEntity(entity, request);
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    @Transactional
    public void delete(String employeeId, String attachmentFileId) {
        AttachmentFile entity = findActive(employeeId, attachmentFileId);
        entity.softDelete();
        repository.save(entity);
    }

    private AttachmentFile findActive(String employeeId, String attachmentFileId) {
        validateEmployee(employeeId);
        return repository.findByAttachmentFileIdAndEmployeeIdAndDeletedAtIsNull(attachmentFileId, employeeId)
                .filter(entity -> Boolean.TRUE.equals(entity.getIsActive()))
                .orElseThrow(() -> new ResourceNotFoundException("Attachment file not found"));
    }

    private void validateEmployee(String employeeId) {
        employeeRepository.findByEmployeeIdAndDeletedAtIsNull(employeeId)
                .filter(employee -> Boolean.TRUE.equals(employee.getIsActive()))
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
    }
}
