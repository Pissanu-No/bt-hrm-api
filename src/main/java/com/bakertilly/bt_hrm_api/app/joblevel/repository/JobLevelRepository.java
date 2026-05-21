package com.bakertilly.bt_hrm_api.app.joblevel.repository;

import com.bakertilly.bt_hrm_api.app.joblevel.entity.JobLevel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JobLevelRepository extends JpaRepository<JobLevel, String> {
    Page<JobLevel> findByIsActiveTrueAndDeletedAtIsNull(Pageable pageable);
    Optional<JobLevel> findByJobLevelIdAndDeletedAtIsNull(String jobLevelId);
    boolean existsByJobLevelCodeAndDeletedAtIsNull(String jobLevelCode);
    boolean existsByJobLevelCodeAndJobLevelIdNotAndDeletedAtIsNull(String jobLevelCode, String jobLevelId);
}
