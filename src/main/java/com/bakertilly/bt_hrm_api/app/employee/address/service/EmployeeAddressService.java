package com.bakertilly.bt_hrm_api.app.employee.address.service;

import com.bakertilly.bt_hrm_api.app.common.response.PagedResponse;
import com.bakertilly.bt_hrm_api.app.employee.address.dto.EmployeeAddressCreateRequest;
import com.bakertilly.bt_hrm_api.app.employee.address.dto.EmployeeAddressResponse;
import com.bakertilly.bt_hrm_api.app.employee.address.dto.EmployeeAddressUpdateRequest;
import org.springframework.data.domain.Pageable;

public interface EmployeeAddressService {
    PagedResponse<EmployeeAddressResponse> findAll(String employeeId, Pageable pageable);

    EmployeeAddressResponse findById(String employeeId, String addressId);

    EmployeeAddressResponse create(String employeeId, EmployeeAddressCreateRequest request);

    EmployeeAddressResponse update(String employeeId, String addressId, EmployeeAddressUpdateRequest request);

    void delete(String employeeId, String addressId);

    EmployeeAddressResponse setRegistered(String employeeId, String addressId);

    EmployeeAddressResponse setContact(String employeeId, String addressId);
}
