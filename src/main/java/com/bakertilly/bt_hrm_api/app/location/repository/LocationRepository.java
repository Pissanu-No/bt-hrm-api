package com.bakertilly.bt_hrm_api.app.location.repository;

import com.bakertilly.bt_hrm_api.app.location.entity.Location;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LocationRepository extends JpaRepository<Location, String> {
    Page<Location> findByIsActiveTrueAndDeletedAtIsNull(Pageable pageable);
    Optional<Location> findByLocationIdAndDeletedAtIsNull(String locationId);
    boolean existsByLocationCodeAndDeletedAtIsNull(String locationCode);
    boolean existsByLocationCodeAndLocationIdNotAndDeletedAtIsNull(String locationCode, String locationId);
}
