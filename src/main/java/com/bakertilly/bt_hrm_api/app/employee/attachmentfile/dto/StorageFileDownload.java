package com.bakertilly.bt_hrm_api.app.employee.attachmentfile.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StorageFileDownload {
    private byte[] content;
    private String fileName;
    private String contentType;
    private long contentLength;
}
