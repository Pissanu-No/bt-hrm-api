package com.bakertilly.bt_hrm_api.app.employee.education.service;

import com.bakertilly.bt_hrm_api.app.common.response.PagedResponse;
import com.bakertilly.bt_hrm_api.app.employee.education.dto.EducationRequest;
import com.bakertilly.bt_hrm_api.app.employee.education.dto.EducationResponse;
import org.springframework.data.domain.Pageable;

public interface EducationService {
    PagedResponse<EducationResponse> findAll(String employeeId, Pageable pageable);
    EducationResponse findById(String employeeId, String educationId);
    EducationResponse create(String employeeId, EducationRequest request);
    EducationResponse update(String employeeId, String educationId, EducationRequest request);
    EducationResponse setHighest(String employeeId, String educationId);
    void delete(String employeeId, String educationId);
}
