package com.bakertilly.bt_hrm_api.app.employee.education.service.impl;

import com.bakertilly.bt_hrm_api.app.common.exception.ResourceNotFoundException;
import com.bakertilly.bt_hrm_api.app.common.response.PagedResponse;
import com.bakertilly.bt_hrm_api.app.employee.education.dto.EducationRequest;
import com.bakertilly.bt_hrm_api.app.employee.education.dto.EducationResponse;
import com.bakertilly.bt_hrm_api.app.employee.education.entity.Education;
import com.bakertilly.bt_hrm_api.app.employee.education.mapper.EducationMapper;
import com.bakertilly.bt_hrm_api.app.employee.education.repository.EducationRepository;
import com.bakertilly.bt_hrm_api.app.employee.education.service.EducationService;
import com.bakertilly.bt_hrm_api.app.employee.entity.Employee;
import com.bakertilly.bt_hrm_api.app.employee.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EducationServiceImpl implements EducationService {
    private final EducationRepository repository;
    private final EmployeeRepository employeeRepository;
    private final EducationMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public PagedResponse<EducationResponse> findAll(String employeeId, Pageable pageable) {
        validateEmployee(employeeId);
        return PagedResponse.from(repository.findByEmployeeIdAndIsActiveTrueAndDeletedAtIsNull(employeeId, pageable)
                .map(mapper::toResponse));
    }

    @Override
    @Transactional(readOnly = true)
    public EducationResponse findById(String employeeId, String educationId) {
        return mapper.toResponse(findActive(employeeId, educationId));
    }

    @Override
    @Transactional
    public EducationResponse create(String employeeId, EducationRequest request) {
        validateEmployee(employeeId);
        validateDates(request);
        Education entity = new Education();
        entity.setEducationId(UUID.randomUUID().toString());
        entity.setEmployeeId(employeeId);
        mapper.updateEntity(entity, request);
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    @Transactional
    public EducationResponse update(String employeeId, String educationId, EducationRequest request) {
        validateDates(request);
        Education entity = findActive(employeeId, educationId);
        mapper.updateEntity(entity, request);
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    @Transactional
    public EducationResponse setHighest(String employeeId, String educationId) {
        Education selected = findActive(employeeId, educationId);
        repository.findByEmployeeIdAndIsActiveTrueAndDeletedAtIsNull(employeeId)
                .forEach(education -> education.setIsHighestEducation(education.getEducationId().equals(educationId)));
        Employee employee = validateEmployee(employeeId);
        employee.setHighestEducationLevel(selected.getEducationLevel());
        employeeRepository.save(employee);
        repository.flush();
        return mapper.toResponse(selected);
    }

    @Override
    @Transactional
    public void delete(String employeeId, String educationId) {
        Education entity = findActive(employeeId, educationId);
        entity.softDelete();
        repository.save(entity);
    }

    private Education findActive(String employeeId, String educationId) {
        validateEmployee(employeeId);
        return repository.findByEducationIdAndEmployeeIdAndDeletedAtIsNull(educationId, employeeId)
                .filter(entity -> Boolean.TRUE.equals(entity.getIsActive()))
                .orElseThrow(() -> new ResourceNotFoundException("Education not found"));
    }

    private Employee validateEmployee(String employeeId) {
        return employeeRepository.findByEmployeeIdAndDeletedAtIsNull(employeeId)
                .filter(employee -> Boolean.TRUE.equals(employee.getIsActive()))
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
    }

    private void validateDates(EducationRequest request) {
        if (request.getStartDate() != null && request.getGraduationDate() != null
                && request.getGraduationDate().isBefore(request.getStartDate())) {
            throw new IllegalArgumentException("Graduation date must not be before start date");
        }
    }
}
