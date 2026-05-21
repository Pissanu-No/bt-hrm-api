package com.bakertilly.bt_hrm_api.app.master.lookup.repository;

import com.bakertilly.bt_hrm_api.app.master.lookup.entity.LookupGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LookupGroupRepository extends JpaRepository<LookupGroup, String> {
    Page<LookupGroup> findByIsActiveTrueAndDeletedAtIsNull(Pageable pageable);
    Optional<LookupGroup> findByLookupGroupIdAndDeletedAtIsNull(String lookupGroupId);
    Optional<LookupGroup> findByLookupGroupCodeAndDeletedAtIsNull(String lookupGroupCode);
    boolean existsByLookupGroupCodeAndDeletedAtIsNull(String lookupGroupCode);
    boolean existsByLookupGroupCodeAndLookupGroupIdNotAndDeletedAtIsNull(String lookupGroupCode, String lookupGroupId);
}
