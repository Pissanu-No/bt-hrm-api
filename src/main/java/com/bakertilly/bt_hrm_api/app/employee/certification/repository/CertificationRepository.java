package com.bakertilly.bt_hrm_api.app.employee.certification.repository;

import com.bakertilly.bt_hrm_api.app.employee.certification.entity.Certification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CertificationRepository extends JpaRepository<Certification, String> {
    Page<Certification> findByEmployeeIdAndIsActiveTrueAndDeletedAtIsNull(String employeeId, Pageable pageable);

    Optional<Certification> findByCertificationIdAndEmployeeIdAndDeletedAtIsNull(String certificationId, String employeeId);
}
