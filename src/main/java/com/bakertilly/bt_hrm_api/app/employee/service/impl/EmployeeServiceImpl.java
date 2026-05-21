package com.bakertilly.bt_hrm_api.app.employee.service.impl;

import com.bakertilly.bt_hrm_api.app.common.exception.DuplicateResourceException;
import com.bakertilly.bt_hrm_api.app.common.exception.ResourceNotFoundException;
import com.bakertilly.bt_hrm_api.app.common.response.PagedResponse;
import com.bakertilly.bt_hrm_api.app.employee.dto.EmployeeListResponse;
import com.bakertilly.bt_hrm_api.app.employee.dto.EmployeeRequest;
import com.bakertilly.bt_hrm_api.app.employee.dto.EmployeeResponse;
import com.bakertilly.bt_hrm_api.app.employee.entity.Employee;
import com.bakertilly.bt_hrm_api.app.employee.mapper.EmployeeMapper;
import com.bakertilly.bt_hrm_api.app.employee.repository.EmployeeRepository;
import com.bakertilly.bt_hrm_api.app.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    @Override
    @Transactional(readOnly = true)
    public PagedResponse<EmployeeListResponse> findAll(Pageable pageable) {
        return PagedResponse.from(employeeRepository.findByIsActiveTrueAndDeletedAtIsNull(pageable)
                .map(employeeMapper::toListResponse));
    }

    @Override
    @Transactional(readOnly = true)
    public EmployeeResponse findById(String employeeId) {
        return employeeMapper.toResponse(findActiveEmployee(employeeId));
    }

    @Override
    @Transactional
    public EmployeeResponse create(EmployeeRequest request) {
        if (employeeRepository.existsByEmployeeCodeAndDeletedAtIsNull(request.getEmployeeCode())) {
            throw new DuplicateResourceException("Employee code already exists");
        }

        Employee employee = new Employee();
        employee.setEmployeeId(UUID.randomUUID().toString());
        employeeMapper.updateEntity(employee, request);
        return employeeMapper.toResponse(employeeRepository.save(employee));
    }

    @Override
    @Transactional
    public EmployeeResponse update(String employeeId, EmployeeRequest request) {
        Employee employee = findActiveEmployee(employeeId);
        if (employeeRepository.existsByEmployeeCodeAndEmployeeIdNotAndDeletedAtIsNull(
                request.getEmployeeCode(), employeeId)) {
            throw new DuplicateResourceException("Employee code already exists");
        }

        employeeMapper.updateEntity(employee, request);
        return employeeMapper.toResponse(employeeRepository.save(employee));
    }

    @Override
    @Transactional
    public void delete(String employeeId) {
        Employee employee = findActiveEmployee(employeeId);
        employee.softDelete();
        employeeRepository.save(employee);
    }

    private Employee findActiveEmployee(String employeeId) {
        return employeeRepository.findByEmployeeIdAndDeletedAtIsNull(employeeId)
                .filter(employee -> Boolean.TRUE.equals(employee.getIsActive()))
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
    }
}
