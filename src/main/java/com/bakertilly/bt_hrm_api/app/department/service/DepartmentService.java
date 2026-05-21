package com.bakertilly.bt_hrm_api.app.department.service;

import com.bakertilly.bt_hrm_api.app.common.response.PagedResponse;
import com.bakertilly.bt_hrm_api.app.department.dto.DepartmentRequest;
import com.bakertilly.bt_hrm_api.app.department.dto.DepartmentResponse;
import org.springframework.data.domain.Pageable;

public interface DepartmentService {
    PagedResponse<DepartmentResponse> findAll(Pageable pageable);

    DepartmentResponse findById(String departmentId);

    DepartmentResponse create(DepartmentRequest request);

    DepartmentResponse update(String departmentId, DepartmentRequest request);

    void delete(String departmentId);
}
