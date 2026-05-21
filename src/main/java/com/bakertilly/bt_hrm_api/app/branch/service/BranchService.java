package com.bakertilly.bt_hrm_api.app.branch.service;

import com.bakertilly.bt_hrm_api.app.branch.dto.BranchRequest;
import com.bakertilly.bt_hrm_api.app.branch.dto.BranchResponse;
import com.bakertilly.bt_hrm_api.app.common.response.PagedResponse;
import org.springframework.data.domain.Pageable;

public interface BranchService {
    PagedResponse<BranchResponse> findAll(Pageable pageable);
    BranchResponse findById(String branchId);
    BranchResponse create(BranchRequest request);
    BranchResponse update(String branchId, BranchRequest request);
    void delete(String branchId);
}
