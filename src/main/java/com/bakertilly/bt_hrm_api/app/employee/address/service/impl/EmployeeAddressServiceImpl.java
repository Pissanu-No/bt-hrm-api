package com.bakertilly.bt_hrm_api.app.employee.address.service.impl;

import com.bakertilly.bt_hrm_api.app.common.exception.ResourceNotFoundException;
import com.bakertilly.bt_hrm_api.app.common.response.PagedResponse;
import com.bakertilly.bt_hrm_api.app.employee.address.dto.EmployeeAddressCreateRequest;
import com.bakertilly.bt_hrm_api.app.employee.address.dto.EmployeeAddressResponse;
import com.bakertilly.bt_hrm_api.app.employee.address.dto.EmployeeAddressUpdateRequest;
import com.bakertilly.bt_hrm_api.app.employee.address.entity.EmployeeAddress;
import com.bakertilly.bt_hrm_api.app.employee.address.mapper.EmployeeAddressMapper;
import com.bakertilly.bt_hrm_api.app.employee.address.repository.EmployeeAddressRepository;
import com.bakertilly.bt_hrm_api.app.employee.address.service.EmployeeAddressService;
import com.bakertilly.bt_hrm_api.app.employee.entity.Employee;
import com.bakertilly.bt_hrm_api.app.employee.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmployeeAddressServiceImpl implements EmployeeAddressService {
    private static final Set<String> ADDRESS_TYPES = Set.of(
            "REGISTERED_ADDRESS", "CONTACT_ADDRESS", "CURRENT_ADDRESS", "MAILING_ADDRESS",
            "TEMPORARY_ADDRESS", "OTHER");

    private final EmployeeAddressRepository repository;
    private final EmployeeRepository employeeRepository;
    private final EmployeeAddressMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public PagedResponse<EmployeeAddressResponse> findAll(String employeeId, Pageable pageable) {
        validateEmployee(employeeId);
        return PagedResponse.from(repository.findByEmployeeIdAndIsActiveTrueAndDeletedAtIsNull(employeeId, pageable)
                .map(mapper::toResponse));
    }

    @Override
    @Transactional(readOnly = true)
    public EmployeeAddressResponse findById(String employeeId, String addressId) {
        return mapper.toResponse(findActive(employeeId, addressId));
    }

    @Override
    @Transactional
    public EmployeeAddressResponse create(String employeeId, EmployeeAddressCreateRequest request) {
        validateEmployee(employeeId);
        validateAddressType(request.getAddressType());
        EmployeeAddress entity = new EmployeeAddress();
        entity.setAddressId(UUID.randomUUID().toString());
        entity.setEmployeeId(employeeId);
        mapper.updateEntity(entity, request);
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    @Transactional
    public EmployeeAddressResponse update(String employeeId, String addressId, EmployeeAddressUpdateRequest request) {
        validateAddressType(request.getAddressType());
        EmployeeAddress entity = findActive(employeeId, addressId);
        mapper.updateEntity(entity, request);
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    @Transactional
    public void delete(String employeeId, String addressId) {
        EmployeeAddress entity = findActive(employeeId, addressId);
        entity.softDelete();
        repository.save(entity);
    }

    @Override
    @Transactional
    public EmployeeAddressResponse setRegistered(String employeeId, String addressId) {
        EmployeeAddress address = findActive(employeeId, addressId);
        Employee employee = validateEmployee(employeeId);
        employee.setRegisteredAddressId(addressId);
        employeeRepository.save(employee);
        return mapper.toResponse(address);
    }

    @Override
    @Transactional
    public EmployeeAddressResponse setContact(String employeeId, String addressId) {
        EmployeeAddress address = findActive(employeeId, addressId);
        Employee employee = validateEmployee(employeeId);
        employee.setContactAddressId(addressId);
        employeeRepository.save(employee);
        return mapper.toResponse(address);
    }

    private EmployeeAddress findActive(String employeeId, String addressId) {
        validateEmployee(employeeId);
        return repository.findByAddressIdAndEmployeeIdAndDeletedAtIsNull(addressId, employeeId)
                .filter(entity -> Boolean.TRUE.equals(entity.getIsActive()))
                .orElseThrow(() -> new ResourceNotFoundException("Employee address not found"));
    }

    private Employee validateEmployee(String employeeId) {
        return employeeRepository.findByEmployeeIdAndDeletedAtIsNull(employeeId)
                .filter(employee -> Boolean.TRUE.equals(employee.getIsActive()))
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
    }

    private void validateAddressType(String addressType) {
        if (!ADDRESS_TYPES.contains(addressType)) {
            throw new IllegalArgumentException("Unsupported addressType");
        }
    }
}
