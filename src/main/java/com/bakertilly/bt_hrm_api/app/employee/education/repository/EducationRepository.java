package com.bakertilly.bt_hrm_api.app.employee.education.repository;

import com.bakertilly.bt_hrm_api.app.employee.education.entity.Education;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EducationRepository extends JpaRepository<Education, String> {
    Page<Education> findByEmployeeIdAndIsActiveTrueAndDeletedAtIsNull(String employeeId, Pageable pageable);

    List<Education> findByEmployeeIdAndIsActiveTrueAndDeletedAtIsNull(String employeeId);

    Optional<Education> findByEducationIdAndEmployeeIdAndDeletedAtIsNull(String educationId, String employeeId);
}
