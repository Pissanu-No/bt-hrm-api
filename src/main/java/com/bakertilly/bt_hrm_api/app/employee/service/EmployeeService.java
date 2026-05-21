package com.bakertilly.bt_hrm_api.app.employee.service;

import com.bakertilly.bt_hrm_api.app.common.response.PagedResponse;
import com.bakertilly.bt_hrm_api.app.employee.dto.EmployeeListResponse;
import com.bakertilly.bt_hrm_api.app.employee.dto.EmployeeRequest;
import com.bakertilly.bt_hrm_api.app.employee.dto.EmployeeResponse;
import org.springframework.data.domain.Pageable;

public interface EmployeeService {
    PagedResponse<EmployeeListResponse> findAll(Pageable pageable);

    EmployeeResponse findById(String employeeId);

    EmployeeResponse create(EmployeeRequest request);

    EmployeeResponse update(String employeeId, EmployeeRequest request);

    void delete(String employeeId);
}
