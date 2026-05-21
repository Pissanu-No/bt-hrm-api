package com.bakertilly.bt_hrm_api.app.employee.attachmentfile.service;

import com.bakertilly.bt_hrm_api.app.common.response.PagedResponse;
import com.bakertilly.bt_hrm_api.app.employee.attachmentfile.dto.AttachmentFileRequest;
import com.bakertilly.bt_hrm_api.app.employee.attachmentfile.dto.AttachmentFileResponse;
import com.bakertilly.bt_hrm_api.app.employee.attachmentfile.dto.AttachmentFileUploadRequest;
import com.bakertilly.bt_hrm_api.app.employee.attachmentfile.dto.StorageFileDownload;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface AttachmentFileService {
    PagedResponse<AttachmentFileResponse> findAll(String employeeId, Pageable pageable);

    AttachmentFileResponse findById(String employeeId, String attachmentFileId);

    AttachmentFileResponse create(String employeeId, AttachmentFileRequest request);

    AttachmentFileResponse upload(String employeeId, MultipartFile file, AttachmentFileUploadRequest request);

    StorageFileDownload download(String employeeId, String attachmentFileId);

    AttachmentFileResponse update(String employeeId, String attachmentFileId, AttachmentFileRequest request);

    void delete(String employeeId, String attachmentFileId);
}
