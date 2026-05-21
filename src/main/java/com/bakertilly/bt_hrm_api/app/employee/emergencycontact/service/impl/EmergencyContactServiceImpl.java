package com.bakertilly.bt_hrm_api.app.employee.emergencycontact.service.impl;

import com.bakertilly.bt_hrm_api.app.common.exception.ResourceNotFoundException;
import com.bakertilly.bt_hrm_api.app.common.response.PagedResponse;
import com.bakertilly.bt_hrm_api.app.employee.emergencycontact.dto.EmergencyContactRequest;
import com.bakertilly.bt_hrm_api.app.employee.emergencycontact.dto.EmergencyContactResponse;
import com.bakertilly.bt_hrm_api.app.employee.emergencycontact.entity.EmergencyContact;
import com.bakertilly.bt_hrm_api.app.employee.emergencycontact.mapper.EmergencyContactMapper;
import com.bakertilly.bt_hrm_api.app.employee.emergencycontact.repository.EmergencyContactRepository;
import com.bakertilly.bt_hrm_api.app.employee.emergencycontact.service.EmergencyContactService;
import com.bakertilly.bt_hrm_api.app.employee.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmergencyContactServiceImpl implements EmergencyContactService {
    private final EmergencyContactRepository repository;
    private final EmployeeRepository employeeRepository;
    private final EmergencyContactMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public PagedResponse<EmergencyContactResponse> findAll(String employeeId, Pageable pageable) {
        validateEmployee(employeeId);
        return PagedResponse.from(repository.findByEmployeeIdAndIsActiveTrueAndDeletedAtIsNull(employeeId, pageable)
                .map(mapper::toResponse));
    }

    @Override
    @Transactional(readOnly = true)
    public EmergencyContactResponse findById(String employeeId, String emergencyContactId) {
        return mapper.toResponse(findActive(employeeId, emergencyContactId));
    }

    @Override
    @Transactional
    public EmergencyContactResponse create(String employeeId, EmergencyContactRequest request) {
        validateEmployee(employeeId);
        EmergencyContact entity = new EmergencyContact();
        entity.setEmergencyContactId(UUID.randomUUID().toString());
        entity.setEmployeeId(employeeId);
        mapper.updateEntity(entity, request);
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    @Transactional
    public EmergencyContactResponse update(String employeeId, String emergencyContactId, EmergencyContactRequest request) {
        EmergencyContact entity = findActive(employeeId, emergencyContactId);
        mapper.updateEntity(entity, request);
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    @Transactional
    public EmergencyContactResponse setPrimary(String employeeId, String emergencyContactId) {
        EmergencyContact selected = findActive(employeeId, emergencyContactId);
        repository.findByEmployeeIdAndIsActiveTrueAndDeletedAtIsNull(employeeId)
                .forEach(contact -> contact.setIsPrimary(contact.getEmergencyContactId().equals(emergencyContactId)));
        repository.flush();
        return mapper.toResponse(selected);
    }

    @Override
    @Transactional
    public void delete(String employeeId, String emergencyContactId) {
        EmergencyContact entity = findActive(employeeId, emergencyContactId);
        entity.softDelete();
        repository.save(entity);
    }

    private EmergencyContact findActive(String employeeId, String emergencyContactId) {
        validateEmployee(employeeId);
        return repository.findByEmergencyContactIdAndEmployeeIdAndDeletedAtIsNull(emergencyContactId, employeeId)
                .filter(entity -> Boolean.TRUE.equals(entity.getIsActive()))
                .orElseThrow(() -> new ResourceNotFoundException("Emergency contact not found"));
    }

    private void validateEmployee(String employeeId) {
        employeeRepository.findByEmployeeIdAndDeletedAtIsNull(employeeId)
                .filter(employee -> Boolean.TRUE.equals(employee.getIsActive()))
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
    }
}
