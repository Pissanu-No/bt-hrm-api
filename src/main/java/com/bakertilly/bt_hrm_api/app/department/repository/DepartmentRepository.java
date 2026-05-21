package com.bakertilly.bt_hrm_api.app.department.repository;

import com.bakertilly.bt_hrm_api.app.department.entity.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, String> {
    Page<Department> findByIsActiveTrueAndDeletedAtIsNull(Pageable pageable);

    Optional<Department> findByDepartmentIdAndDeletedAtIsNull(String departmentId);

    boolean existsByCompanyIdAndDepartmentCodeAndDeletedAtIsNull(String companyId, String departmentCode);

    boolean existsByCompanyIdAndDepartmentCodeAndDepartmentIdNotAndDeletedAtIsNull(
            String companyId, String departmentCode, String departmentId);
}
