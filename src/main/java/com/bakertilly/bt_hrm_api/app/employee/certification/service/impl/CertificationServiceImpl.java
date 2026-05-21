package com.bakertilly.bt_hrm_api.app.employee.certification.service.impl;

import com.bakertilly.bt_hrm_api.app.common.exception.ResourceNotFoundException;
import com.bakertilly.bt_hrm_api.app.common.response.PagedResponse;
import com.bakertilly.bt_hrm_api.app.employee.certification.dto.CertificationRequest;
import com.bakertilly.bt_hrm_api.app.employee.certification.dto.CertificationResponse;
import com.bakertilly.bt_hrm_api.app.employee.certification.entity.Certification;
import com.bakertilly.bt_hrm_api.app.employee.certification.mapper.CertificationMapper;
import com.bakertilly.bt_hrm_api.app.employee.certification.repository.CertificationRepository;
import com.bakertilly.bt_hrm_api.app.employee.certification.service.CertificationService;
import com.bakertilly.bt_hrm_api.app.employee.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CertificationServiceImpl implements CertificationService {
    private static final Set<String> CERTIFICATION_STATUSES = Set.of("ACTIVE", "EXPIRED", "REVOKED", "PENDING", "OTHER");

    private final CertificationRepository repository;
    private final EmployeeRepository employeeRepository;
    private final CertificationMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public PagedResponse<CertificationResponse> findAll(String employeeId, Pageable pageable) {
        validateEmployee(employeeId);
        return PagedResponse.from(repository.findByEmployeeIdAndIsActiveTrueAndDeletedAtIsNull(employeeId, pageable)
                .map(mapper::toResponse));
    }

    @Override
    @Transactional(readOnly = true)
    public CertificationResponse findById(String employeeId, String certificationId) {
        return mapper.toResponse(findActive(employeeId, certificationId));
    }

    @Override
    @Transactional
    public CertificationResponse create(String employeeId, CertificationRequest request) {
        validateEmployee(employeeId);
        validateRequest(request);
        Certification entity = new Certification();
        entity.setCertificationId(UUID.randomUUID().toString());
        entity.setEmployeeId(employeeId);
        mapper.updateEntity(entity, request);
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    @Transactional
    public CertificationResponse update(String employeeId, String certificationId, CertificationRequest request) {
        validateRequest(request);
        Certification entity = findActive(employeeId, certificationId);
        mapper.updateEntity(entity, request);
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    @Transactional
    public void delete(String employeeId, String certificationId) {
        Certification entity = findActive(employeeId, certificationId);
        entity.softDelete();
        repository.save(entity);
    }

    private Certification findActive(String employeeId, String certificationId) {
        validateEmployee(employeeId);
        return repository.findByCertificationIdAndEmployeeIdAndDeletedAtIsNull(certificationId, employeeId)
                .filter(entity -> Boolean.TRUE.equals(entity.getIsActive()))
                .orElseThrow(() -> new ResourceNotFoundException("Certification not found"));
    }

    private void validateEmployee(String employeeId) {
        employeeRepository.findByEmployeeIdAndDeletedAtIsNull(employeeId)
                .filter(employee -> Boolean.TRUE.equals(employee.getIsActive()))
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
    }

    private void validateRequest(CertificationRequest request) {
        String status = request.getCertificationStatus() == null || request.getCertificationStatus().isBlank()
                ? "ACTIVE"
                : request.getCertificationStatus();
        if (!CERTIFICATION_STATUSES.contains(status)) {
            throw new IllegalArgumentException("Unsupported certificationStatus");
        }
        if (!Boolean.TRUE.equals(request.getNeverExpires())
                && request.getIssuedDate() != null
                && request.getExpiredDate() != null
                && request.getExpiredDate().isBefore(request.getIssuedDate())) {
            throw new IllegalArgumentException("Expired date must not be before issued date");
        }
    }
}
