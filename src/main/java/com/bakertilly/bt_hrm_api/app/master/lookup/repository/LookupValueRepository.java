package com.bakertilly.bt_hrm_api.app.master.lookup.repository;

import com.bakertilly.bt_hrm_api.app.master.lookup.entity.LookupValue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LookupValueRepository extends JpaRepository<LookupValue, String> {
    Page<LookupValue> findByIsActiveTrueAndDeletedAtIsNull(Pageable pageable);
    Optional<LookupValue> findByLookupValueIdAndDeletedAtIsNull(String lookupValueId);
    List<LookupValue> findByLookupGroupIdAndIsActiveTrueAndDeletedAtIsNullOrderBySortOrderAscLookupCodeAsc(String lookupGroupId);
    boolean existsByLookupGroupIdAndLookupCodeAndDeletedAtIsNull(String lookupGroupId, String lookupCode);
    boolean existsByLookupGroupIdAndLookupCodeAndLookupValueIdNotAndDeletedAtIsNull(String lookupGroupId, String lookupCode, String lookupValueId);
}
