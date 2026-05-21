package com.bakertilly.bt_hrm_api.app.employee.certification.service;

import com.bakertilly.bt_hrm_api.app.common.response.PagedResponse;
import com.bakertilly.bt_hrm_api.app.employee.certification.dto.CertificationRequest;
import com.bakertilly.bt_hrm_api.app.employee.certification.dto.CertificationResponse;
import org.springframework.data.domain.Pageable;

public interface CertificationService {
    PagedResponse<CertificationResponse> findAll(String employeeId, Pageable pageable);
    CertificationResponse findById(String employeeId, String certificationId);
    CertificationResponse create(String employeeId, CertificationRequest request);
    CertificationResponse update(String employeeId, String certificationId, CertificationRequest request);
    void delete(String employeeId, String certificationId);
}
