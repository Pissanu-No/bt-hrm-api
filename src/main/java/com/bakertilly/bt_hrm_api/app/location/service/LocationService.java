package com.bakertilly.bt_hrm_api.app.location.service;

import com.bakertilly.bt_hrm_api.app.common.response.PagedResponse;
import com.bakertilly.bt_hrm_api.app.location.dto.LocationRequest;
import com.bakertilly.bt_hrm_api.app.location.dto.LocationResponse;
import org.springframework.data.domain.Pageable;

public interface LocationService {
    PagedResponse<LocationResponse> findAll(Pageable pageable);
    LocationResponse findById(String locationId);
    LocationResponse create(LocationRequest request);
    LocationResponse update(String locationId, LocationRequest request);
    void delete(String locationId);
}
