package com.bakertilly.bt_hrm_api.app.employee.attachmentfile.entity;

import com.bakertilly.bt_hrm_api.app.common.persistence.AuditableSoftDeleteEntity;
import com.bakertilly.bt_hrm_api.app.common.persistence.UuidPrimaryKeyEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "hris_transaction_attachment_file", schema = "hris")
public class AttachmentFile extends AuditableSoftDeleteEntity implements UuidPrimaryKeyEntity {
    @Id
    @Column(name = "attachment_file_id", length = 60, nullable = false)
    private String attachmentFileId;

    @Column(name = "employee_id", length = 60, nullable = false)
    private String employeeId;

    @Column(name = "owner_table", length = 100)
    private String ownerTable;

    @Column(name = "owner_id", length = 60)
    private String ownerId;

    @Column(name = "attachment_category", length = 100, nullable = false)
    private String attachmentCategory;

    @Column(name = "attachment_type", length = 100, nullable = false)
    private String attachmentType;

    @Column(name = "attachment_name", nullable = false)
    private String attachmentName;

    @Column(name = "description", length = 1000)
    private String description;

    @Column(name = "file_name", nullable = false)
    private String fileName;

    @Column(name = "original_file_name")
    private String originalFileName;

    @Column(name = "file_extension", length = 50)
    private String fileExtension;

    @Column(name = "mime_type", length = 100)
    private String mimeType;

    @Column(name = "file_size")
    private Long fileSize;

    @Column(name = "storage_provider", length = 50, nullable = false)
    private String storageProvider = "AZURE_BLOB";

    @Column(name = "blob_container_name")
    private String blobContainerName;

    @Column(name = "blob_name", length = 1000)
    private String blobName;

    @Column(name = "file_path", length = 1000)
    private String filePath;

    @Column(name = "file_url", length = 1000)
    private String fileUrl;

    @Column(name = "checksum")
    private String checksum;

    @Column(name = "access_level", length = 50, nullable = false)
    private String accessLevel = "HR_CONFIDENTIAL";

    @Column(name = "is_confidential", nullable = false)
    private Boolean isConfidential = true;

    @Column(name = "uploaded_at", nullable = false)
    private LocalDateTime uploadedAt;

    @Column(name = "uploaded_by", length = 100)
    private String uploadedBy;

    @Override
    public String getPrimaryKeyValue() {
        return attachmentFileId;
    }

    @Override
    public void setPrimaryKeyValue(String primaryKeyValue) {
        attachmentFileId = primaryKeyValue;
    }

    @Override
    public String getPrimaryKeyFieldName() {
        return "attachmentFileId";
    }
}
