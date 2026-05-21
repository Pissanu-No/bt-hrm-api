# API Design

## Base URL

```text
/v1/s
```

## Standard Success Response

```json
{
  "success": true,
  "message": "Success",
  "data": {},
  "timestamp": "2026-05-05T10:00:00+07:00"
}
```

## Standard List Response

```json
{
  "success": true,
  "message": "Success",
  "data": {
    "items": [],
    "page": 0,
    "size": 10,
    "totalItems": 0,
    "totalPages": 0
  },
  "timestamp": "2026-05-05T10:00:00+07:00"
}
```

## Standard Error Response

```json
{
  "success": false,
  "message": "Validation failed",
  "errors": [
    {
      "field": "companyCode",
      "message": "Company code is required"
    }
  ],
  "timestamp": "2026-05-05T10:00:00+07:00"
}
```

## Pagination

List APIs accept Spring pageable query parameters:

```text
page=0
size=10
sort=createdAt,desc
```

Default paging:

```text
page=0
size=10
sort=createdAt,desc
```

## Company API

```text
GET    /v1/s/companies
GET    /v1/s/companies/{companyId}
POST   /v1/s/companies
PUT    /v1/s/companies/{companyId}
DELETE /v1/s/companies/{companyId}
```

Create required fields:

- `companyCode`
- `companyName`

## Department API

```text
GET    /v1/s/departments
GET    /v1/s/departments/{departmentId}
POST   /v1/s/departments
PUT    /v1/s/departments/{departmentId}
DELETE /v1/s/departments/{departmentId}
```

Create required fields:

- `companyId`
- `departmentCode`
- `departmentName`

## Position API

```text
GET    /v1/s/positions
GET    /v1/s/positions/{positionId}
POST   /v1/s/positions
PUT    /v1/s/positions/{positionId}
DELETE /v1/s/positions/{positionId}
```

Create required fields:

- `companyId`
- `positionCode`
- `positionName`

## Employee API

```text
GET    /v1/s/employees
GET    /v1/s/employees/{employeeId}
POST   /v1/s/employees
PUT    /v1/s/employees/{employeeId}
DELETE /v1/s/employees/{employeeId}
```

Create required fields:

- `employeeCode`
- `firstName`
- `lastName`
- `employeeStatus`

## Employee Address API

```text
GET    /v1/s/employees/{employeeId}/addresses
GET    /v1/s/employees/{employeeId}/addresses/{addressId}
POST   /v1/s/employees/{employeeId}/addresses
PUT    /v1/s/employees/{employeeId}/addresses/{addressId}
PUT    /v1/s/employees/{employeeId}/addresses/{addressId}/set-registered
PUT    /v1/s/employees/{employeeId}/addresses/{addressId}/set-contact
DELETE /v1/s/employees/{employeeId}/addresses/{addressId}
```

## Employee Emergency Contact API

```text
GET    /v1/s/employees/{employeeId}/emergency-contacts
GET    /v1/s/employees/{employeeId}/emergency-contacts/{emergencyContactId}
POST   /v1/s/employees/{employeeId}/emergency-contacts
PUT    /v1/s/employees/{employeeId}/emergency-contacts/{emergencyContactId}
PUT    /v1/s/employees/{employeeId}/emergency-contacts/{emergencyContactId}/set-primary
DELETE /v1/s/employees/{employeeId}/emergency-contacts/{emergencyContactId}
```

## Employee Education API

```text
GET    /v1/s/employees/{employeeId}/educations
GET    /v1/s/employees/{employeeId}/educations/{educationId}
POST   /v1/s/employees/{employeeId}/educations
PUT    /v1/s/employees/{employeeId}/educations/{educationId}
PUT    /v1/s/employees/{employeeId}/educations/{educationId}/set-highest
DELETE /v1/s/employees/{employeeId}/educations/{educationId}
```

## Employee Certification API

```text
GET    /v1/s/employees/{employeeId}/certifications
GET    /v1/s/employees/{employeeId}/certifications/{certificationId}
POST   /v1/s/employees/{employeeId}/certifications
PUT    /v1/s/employees/{employeeId}/certifications/{certificationId}
DELETE /v1/s/employees/{employeeId}/certifications/{certificationId}
```

## Employee Attachment File API

```text
GET    /v1/s/employees/{employeeId}/attachment-files
GET    /v1/s/employees/{employeeId}/attachment-files/{attachmentFileId}
POST   /v1/s/employees/{employeeId}/attachment-files
POST   /v1/s/employees/{employeeId}/attachment-files/upload
GET    /v1/s/employees/{employeeId}/attachment-files/{attachmentFileId}/download
PUT    /v1/s/employees/{employeeId}/attachment-files/{attachmentFileId}
DELETE /v1/s/employees/{employeeId}/attachment-files/{attachmentFileId}
```

Attachment files are stored in Azure Blob Storage under:

```text
{containerName}/Profile/{employeeId}/
```

## HTTP Statuses

- `200 OK`: successful read, update, or delete.
- `201 Created`: successful create.
- `400 Bad Request`: validation or malformed request.
- `404 Not Found`: record does not exist or is soft deleted.
- `409 Conflict`: duplicate business key.
- `500 Internal Server Error`: unhandled server error.

## Filtering for First Slice

Initial list APIs should return non-deleted records only:

```text
isActive = true
deletedAt IS NULL
```

Optional filters can be added conservatively:

- Company: `companyCode`, `companyName`, `isActive`
- Department: `companyId`, `departmentCode`, `departmentName`, `isActive`
- Position: `companyId`, `departmentId`, `positionCode`, `positionName`, `isActive`
- Employee: `employeeCode`, `firstName`, `lastName`, `employeeStatus`, `isActive`
