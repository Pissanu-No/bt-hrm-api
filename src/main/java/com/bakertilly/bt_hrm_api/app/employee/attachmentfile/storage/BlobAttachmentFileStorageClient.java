package com.bakertilly.bt_hrm_api.app.employee.attachmentfile.storage;

import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.blob.models.BlobHttpHeaders;
import com.bakertilly.bt_hrm_api.app.employee.attachmentfile.dto.StorageFileDownload;
import com.bakertilly.bt_hrm_api.core.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class BlobAttachmentFileStorageClient {
    private final BlobStorageFileProperties properties;

    public BlobStorageFileUploadResult upload(MultipartFile file, String folderPath) {
        String originalFileName = originalFileName(file);
        String extension = fileExtension(originalFileName);
        String blobName = buildBlobName(folderPath, extension);
        BlobClient blobClient = containerClient().getBlobClient(blobName);

        try {
            blobClient.upload(file.getInputStream(), file.getSize(), true);
            blobClient.setHttpHeaders(new BlobHttpHeaders().setContentType(contentType(file)));
        } catch (IOException exception) {
            throw new ServiceException(HttpStatus.BAD_REQUEST, "INVALID_UPLOAD_FILE", exception,
                    "Invalid upload file");
        } catch (RuntimeException exception) {
            throw new ServiceException(HttpStatus.BAD_GATEWAY, "BLOB_FILE_UPLOAD_FAILED", exception,
                    "Blob storage file upload failed");
        }

        return BlobStorageFileUploadResult.builder()
                .fileName(fileNameFromBlobName(blobName))
                .originalFileName(originalFileName)
                .fileExtension(extension)
                .mimeType(contentType(file))
                .fileSize(file.getSize())
                .blobContainerName(properties.getContainerName())
                .blobName(blobName)
                .filePath(blobName)
                .fileUrl(blobClient.getBlobUrl())
                .checksum(null)
                .build();
    }

    public StorageFileDownload download(String blobName, String fallbackFileName, String fallbackContentType) {
        if (!StringUtils.hasText(blobName)) {
            throw new ServiceException(HttpStatus.BAD_REQUEST, "MISSING_BLOB_NAME", null,
                    "Attachment file does not have blobName");
        }

        BlobClient blobClient = containerClient().getBlobClient(blobName);
        if (!blobClient.exists()) {
            throw new ServiceException(HttpStatus.NOT_FOUND, "BLOB_FILE_NOT_FOUND", null,
                    "Blob file not found");
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            blobClient.downloadStream(outputStream);
        } catch (RuntimeException exception) {
            throw new ServiceException(HttpStatus.BAD_GATEWAY, "BLOB_FILE_DOWNLOAD_FAILED", exception,
                    "Blob storage file download failed");
        }

        String contentType = StringUtils.hasText(fallbackContentType)
                ? fallbackContentType
                : blobClient.getProperties().getContentType();
        byte[] content = outputStream.toByteArray();

        return StorageFileDownload.builder()
                .content(content)
                .contentLength(content.length)
                .fileName(StringUtils.hasText(fallbackFileName) ? fallbackFileName : fileNameFromBlobName(blobName))
                .contentType(contentType)
                .build();
    }

    private BlobContainerClient containerClient() {
        BlobContainerClient containerClient = blobServiceClient().getBlobContainerClient(properties.getContainerName());
        if (!containerClient.exists()) {
            containerClient.create();
        }
        return containerClient;
    }

    private BlobServiceClient blobServiceClient() {
        BlobServiceClientBuilder builder = new BlobServiceClientBuilder();
        if (StringUtils.hasText(properties.getConnectionString())) {
            return builder.connectionString(properties.getConnectionString()).buildClient();
        }
        if (StringUtils.hasText(properties.getEndpoint())) {
            return builder.endpoint(properties.getEndpoint())
                    .credential(new DefaultAzureCredentialBuilder().build())
                    .buildClient();
        }
        throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, "BLOB_STORAGE_NOT_CONFIGURED", null,
                "Blob storage is not configured");
    }

    private String buildBlobName(String folderPath, String extension) {
        String fileName = UUID.randomUUID() + (StringUtils.hasText(extension) ? "." + extension : "");
        if (!StringUtils.hasText(folderPath)) {
            return fileName;
        }
        return trimSlash(folderPath) + "/" + fileName;
    }

    private String originalFileName(MultipartFile file) {
        return StringUtils.hasText(file.getOriginalFilename()) ? file.getOriginalFilename() : "attachment";
    }

    private String fileExtension(String fileName) {
        String extension = StringUtils.getFilenameExtension(fileName);
        return StringUtils.hasText(extension) ? extension : null;
    }

    private String contentType(MultipartFile file) {
        return StringUtils.hasText(file.getContentType()) ? file.getContentType() : "application/octet-stream";
    }

    private String fileNameFromBlobName(String blobName) {
        int slashIndex = blobName.lastIndexOf('/');
        String fileName = slashIndex >= 0 ? blobName.substring(slashIndex + 1) : blobName;
        return StringUtils.hasText(fileName) ? fileName : "attachment";
    }

    private String trimSlash(String value) {
        String trimmed = value;
        while (trimmed.startsWith("/")) {
            trimmed = trimmed.substring(1);
        }
        while (trimmed.endsWith("/")) {
            trimmed = trimmed.substring(0, trimmed.length() - 1);
        }
        return URLEncoder.encode(trimmed, StandardCharsets.UTF_8).replace("%2F", "/");
    }
}
