package com.bakertilly.bt_hrm_api.app.position.service;

import com.bakertilly.bt_hrm_api.app.common.response.PagedResponse;
import com.bakertilly.bt_hrm_api.app.position.dto.PositionRequest;
import com.bakertilly.bt_hrm_api.app.position.dto.PositionResponse;
import org.springframework.data.domain.Pageable;

public interface PositionService {
    PagedResponse<PositionResponse> findAll(Pageable pageable);

    PositionResponse findById(String positionId);

    PositionResponse create(PositionRequest request);

    PositionResponse update(String positionId, PositionRequest request);

    void delete(String positionId);
}
