package com.bakertilly.bt_hrm_api.app.costcenter.service;

import com.bakertilly.bt_hrm_api.app.common.response.PagedResponse;
import com.bakertilly.bt_hrm_api.app.costcenter.dto.CostCenterRequest;
import com.bakertilly.bt_hrm_api.app.costcenter.dto.CostCenterResponse;
import org.springframework.data.domain.Pageable;

public interface CostCenterService {
    PagedResponse<CostCenterResponse> findAll(Pageable pageable);
    CostCenterResponse findById(String costCenterId);
    CostCenterResponse create(CostCenterRequest request);
    CostCenterResponse update(String costCenterId, CostCenterRequest request);
    void delete(String costCenterId);
}
