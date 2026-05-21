package com.bakertilly.bt_hrm_api.app.employee.attachmentfile.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Employee attachment file upload metadata request")
public class AttachmentFileUploadRequest {
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

    @Size(max = 255)
    private String attachmentName;

    @Size(max = 1000)
    private String description;

    @Size(max = 50)
    private String accessLevel;

    private Boolean isConfidential;
}
