package com.bakertilly.bt_hrm_api.app.employee.repository;

import com.bakertilly.bt_hrm_api.app.employee.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
    Page<Employee> findByIsActiveTrueAndDeletedAtIsNull(Pageable pageable);

    Optional<Employee> findByEmployeeIdAndDeletedAtIsNull(String employeeId);

    boolean existsByEmployeeCodeAndDeletedAtIsNull(String employeeCode);

    boolean existsByEmployeeCodeAndEmployeeIdNotAndDeletedAtIsNull(String employeeCode, String employeeId);
}
