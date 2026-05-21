# Database Mapping

## Schema

All Phase 1 HRIS entities map to SQL Server schema:

```text
hris
```

IDs are Java `String` and SQL Server `nvarchar(60)`.

## Shared Columns

Most master/security/employee tables include:

| Column | Java Type | Notes |
| --- | --- | --- |
| `isActive` | `Boolean` | Defaults to `true`; set to `false` on soft delete. |
| `createdAt` | `OffsetDateTime` or `LocalDateTime` | SQL Server `datetime2(7)`. |
| `createdBy` | `String` | Nullable for first slice. |
| `updatedAt` | `OffsetDateTime` or `LocalDateTime` | Nullable. |
| `updatedBy` | `String` | Nullable. |
| `deletedAt` | `OffsetDateTime` or `LocalDateTime` | Set on soft delete. |

Recommended Java time type for implementation: `LocalDateTime`, because SQL Server `datetime2` has no timezone offset.

## Company

Table: `[hris].[hris_master_company]`

| Column | Java Field | Java Type | Constraint |
| --- | --- | --- | --- |
| `companyId` | `companyId` | `String` | PK |
| `companyCode` | `companyCode` | `String` | Required, unique |
| `companyName` | `companyName` | `String` | Required |
| `companyNameLocal` | `companyNameLocal` | `String` | Optional |
| `taxId` | `taxId` | `String` | Optional |
| `registrationNo` | `registrationNo` | `String` | Optional |
| `email` | `email` | `String` | Optional |
| `phone` | `phone` | `String` | Optional |
| `website` | `website` | `String` | Optional |
| `addressLine1` | `addressLine1` | `String` | Optional |
| `addressLine2` | `addressLine2` | `String` | Optional |
| `province` | `province` | `String` | Optional |
| `country` | `country` | `String` | Optional |
| `postalCode` | `postalCode` | `String` | Optional |

Unique key:

```text
uq_hris_master_company_companyCode(companyCode)
```

## Department

Table: `[hris].[hris_master_department]`

| Column | Java Field | Java Type | Constraint |
| --- | --- | --- | --- |
| `departmentId` | `departmentId` | `String` | PK |
| `companyId` | `companyId` | `String` | Required, FK to `hris_master_company` |
| `branchId` | `branchId` | `String` | Optional, FK to `hris_master_branch` |
| `costCenterId` | `costCenterId` | `String` | Optional, FK to `hris_master_cost_center` |
| `parentDepartmentId` | `parentDepartmentId` | `String` | Optional, self FK |
| `departmentCode` | `departmentCode` | `String` | Required |
| `departmentName` | `departmentName` | `String` | Required |
| `departmentNameLocal` | `departmentNameLocal` | `String` | Optional |
| `description` | `description` | `String` | Optional |

Unique key:

```text
uq_hris_master_department_company_departmentCode(companyId, departmentCode)
```

## Position

Table: `[hris].[hris_master_position]`

| Column | Java Field | Java Type | Constraint |
| --- | --- | --- | --- |
| `positionId` | `positionId` | `String` | PK |
| `companyId` | `companyId` | `String` | Required, FK to `hris_master_company` |
| `departmentId` | `departmentId` | `String` | Optional, FK to `hris_master_department` |
| `jobLevelId` | `jobLevelId` | `String` | Optional, FK to `hris_master_job_level` |
| `jobFamilyId` | `jobFamilyId` | `String` | Optional, FK to `mstJobFamily` |
| `positionCode` | `positionCode` | `String` | Required |
| `positionName` | `positionName` | `String` | Required |
| `positionNameLocal` | `positionNameLocal` | `String` | Optional |
| `description` | `description` | `String` | Optional |
| `isManagerPosition` | `isManagerPosition` | `Boolean` | Required, default `false` |

Unique key:

```text
uq_hris_master_position_company_positionCode(companyId, positionCode)
```

## Employee

Table: `[hris].[hris_transaction_employee]`

| Column | Java Field | Java Type | Constraint |
| --- | --- | --- | --- |
| `employeeId` | `employeeId` | `String` | PK |
| `employeeCode` | `employeeCode` | `String` | Required, unique |
| `titleName` | `titleName` | `String` | Optional |
| `firstName` | `firstName` | `String` | Required |
| `middleName` | `middleName` | `String` | Optional |
| `lastName` | `lastName` | `String` | Required |
| `titleNameLocal` | `titleNameLocal` | `String` | Optional |
| `firstNameLocal` | `firstNameLocal` | `String` | Optional |
| `middleNameLocal` | `middleNameLocal` | `String` | Optional |
| `lastNameLocal` | `lastNameLocal` | `String` | Optional |
| `preferredName` | `preferredName` | `String` | Optional |
| `gender` | `gender` | `String` | Optional |
| `birthDate` | `birthDate` | `LocalDate` | Optional |
| `nationality` | `nationality` | `String` | Optional |
| `address` | `address` | `String` | Optional |
| `maritalStatus` | `maritalStatus` | `String` | Optional |
| `bloodType` | `bloodType` | `String` | Optional |
| `nationalId` | `nationalId` | `String` | Optional, indexed |
| `passportNo` | `passportNo` | `String` | Optional, indexed |
| `taxNo` | `taxNo` | `String` | Optional |
| `socialSecurityNo` | `socialSecurityNo` | `String` | Optional |
| `highestEducationLevel` | `highestEducationLevel` | `String` | Optional |
| `personalEmail` | `personalEmail` | `String` | Optional |
| `workEmail` | `workEmail` | `String` | Optional |
| `mobilePhone` | `mobilePhone` | `String` | Optional |
| `employeeStatus` | `employeeStatus` | `String` | Required |
| `profileImageUrl` | `profileImageUrl` | `String` | Optional |

Unique key:

```text
uq_hris_transaction_employee_employeeCode(employeeCode)
```

## Later Phase 1 Tables

- Branch: `[hris].[hris_master_branch]`
- User: `[hris].[hris_master_user]`
- Role: `[hris].[hris_master_role]`
- Permission: `[hris].[hris_master_permission]`
- User Role: `[hris].[hris_master_user_role]`
- Role Permission: `[hris].[hris_master_role_permission]`
- Audit Log: `[hris].[hris_transaction_audit_log]`

## Employee Address

Table: `[hris].[hris_transaction_address]`

Primary key: `addressId`

Required fields:

- `employeeId`
- `addressType`

Defaults handled by service/mapper:

- `country=Thailand`
- `isPrimary=false`
- `isSameAsRegisteredAddress=false`

Implemented as nested employee resource:

```text
/v1/s/employees/{employeeId}/addresses
```

Special operations update employee address references:

- `registeredAddressId`
- `contactAddressId`

## Employee Emergency Contact

Table: `[hris].[hris_transaction_emergency_contact]`

Primary key: `emergencyContactId`

Required fields:

- `employeeId`
- `contactName`
- `relationship`

Implemented as nested employee resource:

```text
/v1/s/employees/{employeeId}/emergency-contacts
```

## Employee Education

Table: `[hris].[hris_transaction_education]`

Primary key: `educationId`

Required fields:

- `employeeId`
- `educationLevel`
- `institutionName`

Implemented as nested employee resource:

```text
/v1/s/employees/{employeeId}/educations
```

## Employee Certification

Table: `[hris].[hris_transaction_certification]`

Primary key: `certificationId`

Required fields:

- `employeeId`
- `certificationName`

Defaults handled by service/mapper:

- `neverExpires=false`
- `certificationStatus=ACTIVE`

Implemented as nested employee resource:

```text
/v1/s/employees/{employeeId}/certifications
```

## Employee Attachment File

Table: `[hris].[hris_transaction_attachment_file]`

Primary key: `attachmentFileId`

Required fields:

- `employeeId`
- `attachmentCategory`
- `attachmentType`
- `attachmentName`
- `fileName`

Defaults handled by service/mapper:

- `storageProvider=AZURE_BLOB`
- `accessLevel=HR_CONFIDENTIAL`
- `isConfidential=true`
- `uploadedAt=now`

Blob path convention:

```text
{containerName}/Profile/{employeeId}/
```

Implemented as nested employee resource:

```text
/v1/s/employees/{employeeId}/attachment-files
```

## ID Generation

Recommended first-slice ID generation:

```text
UUID.randomUUID().toString()
```

This produces 36-character IDs, compatible with `nvarchar(60)`.
