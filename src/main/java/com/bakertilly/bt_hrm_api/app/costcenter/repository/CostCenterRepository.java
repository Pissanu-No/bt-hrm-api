package com.bakertilly.bt_hrm_api.app.costcenter.repository;

import com.bakertilly.bt_hrm_api.app.costcenter.entity.CostCenter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CostCenterRepository extends JpaRepository<CostCenter, String> {
    Page<CostCenter> findByIsActiveTrueAndDeletedAtIsNull(Pageable pageable);
    Optional<CostCenter> findByCostCenterIdAndDeletedAtIsNull(String costCenterId);
    boolean existsByCostCenterCodeAndDeletedAtIsNull(String costCenterCode);
    boolean existsByCostCenterCodeAndCostCenterIdNotAndDeletedAtIsNull(String costCenterCode, String costCenterId);
}
