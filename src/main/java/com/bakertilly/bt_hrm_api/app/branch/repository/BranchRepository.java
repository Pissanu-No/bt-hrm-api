package com.bakertilly.bt_hrm_api.app.branch.repository;

import com.bakertilly.bt_hrm_api.app.branch.entity.Branch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BranchRepository extends JpaRepository<Branch, String> {
    Page<Branch> findByIsActiveTrueAndDeletedAtIsNull(Pageable pageable);
    Optional<Branch> findByBranchIdAndDeletedAtIsNull(String branchId);
    boolean existsByBranchCodeAndDeletedAtIsNull(String branchCode);
    boolean existsByBranchCodeAndBranchIdNotAndDeletedAtIsNull(String branchCode, String branchId);
}
