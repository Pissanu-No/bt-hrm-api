package com.bakertilly.bt_hrm_api.app.employee.emergencycontact.repository;

import com.bakertilly.bt_hrm_api.app.employee.emergencycontact.entity.EmergencyContact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmergencyContactRepository extends JpaRepository<EmergencyContact, String> {
    Page<EmergencyContact> findByEmployeeIdAndIsActiveTrueAndDeletedAtIsNull(String employeeId, Pageable pageable);

    List<EmergencyContact> findByEmployeeIdAndIsActiveTrueAndDeletedAtIsNull(String employeeId);

    Optional<EmergencyContact> findByEmergencyContactIdAndEmployeeIdAndDeletedAtIsNull(String emergencyContactId, String employeeId);
}
