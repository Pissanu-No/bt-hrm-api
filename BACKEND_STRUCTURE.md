# Backend Structure

## Proposed Package Structure

Base package:

```text
com.bakertilly.bt_hrm_api
```

Proposed structure:

```text
core
  configuration
  exception
  model
    response
  persistence
  utils
app
  company
    controller
    dto
    entity
    mapper
    repository
    service
      impl
  department
    controller
    dto
    entity
    mapper
    repository
    service
      impl
  position
    controller
    dto
    entity
    mapper
    repository
    service
      impl
  employee
    controller
    dto
    entity
    mapper
    repository
    service
      impl
  branch
  security
    user
    role
    permission
  audit
```

## Layer Responsibilities

- `entity`: JPA entities mapped to `[hris]` schema tables.
- `dto`: request and response DTOs with validation and Swagger schemas.
- `repository`: Spring Data JPA repositories.
- `mapper`: explicit entity/DTO mapping. No entity exposure from controllers.
- `service`: business interface.
- `service.impl`: transaction boundaries, validations, duplicate checks, soft delete logic.
- `controller`: REST endpoints, Swagger annotations, pagination parameters, standard responses.

## Shared Foundation Classes

Proposed shared classes:

```text
core/model/response/ApiResponse.java
core/model/response/PagedResponse.java
core/model/response/ApiError.java
core/exception/ResourceNotFoundException.java
core/exception/DuplicateResourceException.java
core/persistence/AuditableSoftDeleteEntity.java
```

## Naming Conventions

- Entity names: `Company`, `Department`, `Position`, `Employee`.
- Request DTOs: `CreateCompanyRequest`, `UpdateCompanyRequest`.
- Response DTOs: `CompanyResponse`.
- Mapper names: `CompanyMapper`.
- Repository names: `CompanyRepository`.
- Service names: `CompanyService`, `CompanyServiceImpl`.
- Controller names: `CompanyController`.

## First Vertical Slice Packages

```text
app/company
app/department
app/position
app/employee
```

## Later Phase 1 Packages

```text
app/branch
app/security/user
app/security/role
app/security/permission
app/audit
```
