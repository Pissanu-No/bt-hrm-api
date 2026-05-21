package com.bakertilly.bt_hrm_api.app.company.service;

import com.bakertilly.bt_hrm_api.app.common.response.PagedResponse;
import com.bakertilly.bt_hrm_api.app.company.dto.CompanyRequest;
import com.bakertilly.bt_hrm_api.app.company.dto.CompanyResponse;
import org.springframework.data.domain.Pageable;

public interface CompanyService {
    PagedResponse<CompanyResponse> findAll(Pageable pageable);

    CompanyResponse findById(String companyId);

    CompanyResponse create(CompanyRequest request);

    CompanyResponse update(String companyId, CompanyRequest request);

    void delete(String companyId);
}
