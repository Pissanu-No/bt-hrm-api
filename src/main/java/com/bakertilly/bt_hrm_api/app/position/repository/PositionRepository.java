package com.bakertilly.bt_hrm_api.app.position.repository;

import com.bakertilly.bt_hrm_api.app.position.entity.Position;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PositionRepository extends JpaRepository<Position, String> {
    Page<Position> findByIsActiveTrueAndDeletedAtIsNull(Pageable pageable);

    Optional<Position> findByPositionIdAndDeletedAtIsNull(String positionId);

    boolean existsByCompanyIdAndPositionCodeAndDeletedAtIsNull(String companyId, String positionCode);

    boolean existsByCompanyIdAndPositionCodeAndPositionIdNotAndDeletedAtIsNull(
            String companyId, String positionCode, String positionId);
}
