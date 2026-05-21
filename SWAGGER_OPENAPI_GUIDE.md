# Swagger / OpenAPI Guide

## Dependency

`pom.xml` already includes:

```xml
<dependency>
  <groupId>org.springdoc</groupId>
  <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
  <version>${springdoc-openapi.version}</version>
</dependency>
```

No duplicate springdoc dependency should be added.

## Endpoints

Swagger UI:

```text
/swagger-ui/index.html
```

OpenAPI JSON:

```text
/v3/api-docs
```

## OpenAPI Metadata

Update `OpenApiConfiguration` to:

```text
Title: HRIS + HRD Platform API
Version: v1
Description: REST API documentation for Enterprise HRIS + HRD Platform
```

## Proposed Groups

Use `GroupedOpenApi` groups:

| Group | Package |
| --- | --- |
| `Core Master Data` | `com.bakertilly.bt_hrm_api.app.company`, `department`, `position`, `branch` |
| `Employee Management` | `com.bakertilly.bt_hrm_api.app.employee` |
| `Security` | `com.bakertilly.bt_hrm_api.app.security` |
| `Audit Log` | `com.bakertilly.bt_hrm_api.app.audit` |

For the first vertical slice, only Core Master Data and Employee Management will expose endpoints.

## Controller Annotations

Each controller should include:

```java
@Tag(name = "Company", description = "Company master data APIs")
@Operation(summary = "Create company")
@ApiResponses(...)
@Parameter(...)
```

## DTO Schema Annotations

Request and response DTOs should include:

```java
@Schema(description = "Company code", example = "BT")
```

Validation annotations should be visible in generated schemas where possible:

```java
@NotBlank(message = "Company code is required")
@Size(max = 50)
@Email
```

## Standard Response Documentation

Swagger should document:

- `ApiResponse<T>` for single-object success responses.
- `ApiResponse<PagedResponse<T>>` for list responses.
- `ApiResponse<Void>` for delete responses.
- `multipart/form-data` for attachment upload operations.
- Binary `application/octet-stream` response for attachment download operations.
- Error response with `errors[]` containing field and message.

Because generic response wrappers can be hard to render perfectly in OpenAPI, each controller operation should explicitly describe response examples using `@ApiResponse` and `@Content`.

## Security Compatibility

Swagger must remain public while business APIs remain protected according to project security rules.

Permit these paths:

```text
/swagger-ui/**
/swagger-ui.html
/v3/api-docs/**
/v3/api-docs
```

Do not disable security globally for business APIs.

## Blob Storage File Integration

Employee attachment upload/download APIs store files directly in Azure Blob Storage.

```text
{containerName}/Profile/{employeeId}/
```

```properties
bt.hris.storage.blob.container-name=${HRIS_BLOB_CONTAINER:hris}
bt.hris.storage.blob.connection-string=${HRIS_BLOB_CONNECTION_STRING:}
bt.hris.storage.blob.endpoint=${HRIS_BLOB_ENDPOINT:}
```

Use `HRIS_BLOB_CONNECTION_STRING` for connection string authentication, or `HRIS_BLOB_ENDPOINT` with managed identity/default Azure credential.

## Current Repository Adjustment Needed

The existing `OpenApiConfiguration` still references old package groups under `com.centra_service_api.*`. Replace them with the HRIS package groups above.
