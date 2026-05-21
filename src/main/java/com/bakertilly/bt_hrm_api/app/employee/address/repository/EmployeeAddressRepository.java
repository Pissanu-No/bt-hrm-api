package com.bakertilly.bt_hrm_api.app.employee.address.repository;

import com.bakertilly.bt_hrm_api.app.employee.address.entity.EmployeeAddress;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeAddressRepository extends JpaRepository<EmployeeAddress, String> {
    Page<EmployeeAddress> findByEmployeeIdAndIsActiveTrueAndDeletedAtIsNull(String employeeId, Pageable pageable);

    Optional<EmployeeAddress> findByAddressIdAndEmployeeIdAndDeletedAtIsNull(String addressId, String employeeId);
}
