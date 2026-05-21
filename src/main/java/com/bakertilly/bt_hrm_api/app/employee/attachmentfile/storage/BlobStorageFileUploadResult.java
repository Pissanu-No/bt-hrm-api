package com.bakertilly.bt_hrm_api.app.employee.attachmentfile.storage;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BlobStorageFileUploadResult {
    private String fileName;
    private String originalFileName;
    private String fileExtension;
    private String mimeType;
    private Long fileSize;
    private String blobContainerName;
    private String blobName;
    private String filePath;
    private String fileUrl;
    private String checksum;
}
