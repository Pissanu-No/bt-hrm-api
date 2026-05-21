package com.bakertilly.bt_hrm_api.app.employee.attachmentfile.repository;

import com.bakertilly.bt_hrm_api.app.employee.attachmentfile.entity.AttachmentFile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AttachmentFileRepository extends JpaRepository<AttachmentFile, String> {
    Page<AttachmentFile> findByEmployeeIdAndIsActiveTrueAndDeletedAtIsNull(String employeeId, Pageable pageable);

    Optional<AttachmentFile> findByAttachmentFileIdAndEmployeeIdAndDeletedAtIsNull(String attachmentFileId, String employeeId);
}
