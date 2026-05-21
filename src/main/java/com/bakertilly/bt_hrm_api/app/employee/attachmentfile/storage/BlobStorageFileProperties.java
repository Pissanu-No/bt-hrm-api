package com.bakertilly.bt_hrm_api.app.employee.attachmentfile.storage;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "bt.hris.storage.blob")
public class BlobStorageFileProperties {
    private String containerName = "hris";
    private String connectionString;
    private String endpoint;
}
