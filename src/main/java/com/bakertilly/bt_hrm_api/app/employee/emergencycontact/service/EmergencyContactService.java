package com.bakertilly.bt_hrm_api.app.employee.emergencycontact.service;

import com.bakertilly.bt_hrm_api.app.common.response.PagedResponse;
import com.bakertilly.bt_hrm_api.app.employee.emergencycontact.dto.EmergencyContactRequest;
import com.bakertilly.bt_hrm_api.app.employee.emergencycontact.dto.EmergencyContactResponse;
import org.springframework.data.domain.Pageable;

public interface EmergencyContactService {
    PagedResponse<EmergencyContactResponse> findAll(String employeeId, Pageable pageable);
    EmergencyContactResponse findById(String employeeId, String emergencyContactId);
    EmergencyContactResponse create(String employeeId, EmergencyContactRequest request);
    EmergencyContactResponse update(String employeeId, String emergencyContactId, EmergencyContactRequest request);
    EmergencyContactResponse setPrimary(String employeeId, String emergencyContactId);
    void delete(String employeeId, String emergencyContactId);
}
