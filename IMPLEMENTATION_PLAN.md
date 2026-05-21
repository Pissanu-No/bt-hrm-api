# HRIS Phase 1 Implementation Plan

## Repository Inspection Summary

- Application package: `com.bakertilly.bt_hrm_api`.
- Current source layout is mostly `core/*` only. No HRIS business modules exist yet.
- Maven is configured for Java 21 and includes Spring Data JPA, Spring Security, SQL Server JDBC, validation, Swagger annotations, and `springdoc-openapi-starter-webmvc-ui`.
- `pom.xml` currently uses Spring Boot parent `4.0.6` while also defining `spring-boot.version=3.5.8`. Phase 1 should align with Spring Boot 3 as requested.
- `OpenApiConfiguration` exists, but it still describes "Centra Service API" and groups packages under `com.centra_service_api.*`.
- `SecurityConfig` already permits Swagger paths, but several core classes still import `com.centra_service_api.*`, which must be corrected before the application can be reliably runnable.
- Existing response model `ResponseBodyModel` does not match the required API standard. Phase 1 should introduce a new standard API response model and migrate new controllers to it.
- Provided SQL file `/Users/pissanu/Downloads/hris.sql` defines schema `[hris]` with String IDs as `nvarchar(60)`, camelCase column names, `isActive`, `createdAt`, `updatedAt`, and `deletedAt`.

## Phase 1 Scope

Phase 1 foundation modules:

1. Company
2. Branch
3. Department
4. Position
5. Employee
6. User
7. Role
8. Permission
9. Audit Log

First working vertical slice:

1. Company CRUD API
2. Department CRUD API
3. Position CRUD API
4. Employee basic CRUD API

Branch, User, Role, Permission, and Audit Log are designed in Phase 1 docs but should be implemented after the first vertical slice is stable.

## Implementation Sequence

1. Foundation cleanup
   - Align Spring Boot version with Spring Boot 3.
   - Correct stale package imports from `com.centra_service_api` to `com.bakertilly.bt_hrm_api`.
   - Update OpenAPI metadata to HRIS + HRD Platform.
   - Keep Swagger endpoints permitted in Spring Security.

2. Shared API foundation
   - Add standard `ApiResponse<T>`.
   - Add `PagedResponse<T>`.
   - Add `ApiError` / validation error DTO.
   - Update global exception handling for validation, not found, duplicate, and generic errors.
   - Add reusable audit/base entity fields where useful.

3. Company vertical slice
   - Entity, DTOs, repository, mapper, service, controller.
   - Pagination list endpoint.
   - Soft delete endpoint.
   - Swagger annotations.
   - Basic validation and duplicate checks.

4. Department vertical slice
   - Same layer set.
   - Validate required `companyId`.
   - Validate optional references where implemented.
   - Pagination and soft delete.

5. Position vertical slice
   - Same layer set.
   - Validate required `companyId`.
   - Validate optional `departmentId`.
   - Pagination and soft delete.

6. Employee basic vertical slice
   - Basic `hris_transaction_employee` table only.
   - No employment assignment workflow in first slice.
   - Pagination and soft delete.

7. Verification
   - Run `./mvnw test`.
   - Run application startup check if local configuration allows it.
   - Confirm Swagger UI at `/swagger-ui/index.html`.
   - Confirm OpenAPI JSON at `/v3/api-docs`.

## Rules Applied

- Controllers return DTOs only, never JPA entities.
- IDs are `String`.
- Database fields use camelCase column names.
- SQL Server compatibility is preserved with `nvarchar`, `datetime2`, and schema `hris`.
- List endpoints use Spring `Pageable` and return the required list response format.
- Delete endpoints perform soft delete by setting `isActive=false` and `deletedAt`.
- New APIs use validation annotations and Swagger schema annotations.
- Business APIs remain secured according to project security policy; Swagger endpoints remain public.

## Approval Gate

No business-code implementation should start until this plan, package structure, API design, database mapping, Swagger setup, and proposed file list are approved.
