package com.bakertilly.bt_hrm_api.app.department.service.impl;

import com.bakertilly.bt_hrm_api.app.common.exception.DuplicateResourceException;
import com.bakertilly.bt_hrm_api.app.common.exception.ResourceNotFoundException;
import com.bakertilly.bt_hrm_api.app.common.response.PagedResponse;
import com.bakertilly.bt_hrm_api.app.company.repository.CompanyRepository;
import com.bakertilly.bt_hrm_api.app.department.dto.DepartmentRequest;
import com.bakertilly.bt_hrm_api.app.department.dto.DepartmentResponse;
import com.bakertilly.bt_hrm_api.app.department.entity.Department;
import com.bakertilly.bt_hrm_api.app.department.mapper.DepartmentMapper;
import com.bakertilly.bt_hrm_api.app.department.repository.DepartmentRepository;
import com.bakertilly.bt_hrm_api.app.department.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final CompanyRepository companyRepository;
    private final DepartmentMapper departmentMapper;

    @Override
    @Transactional(readOnly = true)
    public PagedResponse<DepartmentResponse> findAll(Pageable pageable) {
        return PagedResponse.from(departmentRepository.findByIsActiveTrueAndDeletedAtIsNull(pageable)
                .map(departmentMapper::toResponse));
    }

    @Override
    @Transactional(readOnly = true)
    public DepartmentResponse findById(String departmentId) {
        return departmentMapper.toResponse(findActiveDepartment(departmentId));
    }

    @Override
    @Transactional
    public DepartmentResponse create(DepartmentRequest request) {
        validateCompany(request.getCompanyId());
        if (departmentRepository.existsByCompanyIdAndDepartmentCodeAndDeletedAtIsNull(
                request.getCompanyId(), request.getDepartmentCode())) {
            throw new DuplicateResourceException("Department code already exists for this company");
        }

        Department department = new Department();
        department.setDepartmentId(UUID.randomUUID().toString());
        departmentMapper.updateEntity(department, request);
        return departmentMapper.toResponse(departmentRepository.save(department));
    }

    @Override
    @Transactional
    public DepartmentResponse update(String departmentId, DepartmentRequest request) {
        Department department = findActiveDepartment(departmentId);
        validateCompany(request.getCompanyId());
        if (departmentRepository.existsByCompanyIdAndDepartmentCodeAndDepartmentIdNotAndDeletedAtIsNull(
                request.getCompanyId(), request.getDepartmentCode(), departmentId)) {
            throw new DuplicateResourceException("Department code already exists for this company");
        }

        departmentMapper.updateEntity(department, request);
        return departmentMapper.toResponse(departmentRepository.save(department));
    }

    @Override
    @Transactional
    public void delete(String departmentId) {
        Department department = findActiveDepartment(departmentId);
        department.softDelete();
        departmentRepository.save(department);
    }

    private Department findActiveDepartment(String departmentId) {
        return departmentRepository.findByDepartmentIdAndDeletedAtIsNull(departmentId)
                .filter(department -> Boolean.TRUE.equals(department.getIsActive()))
                .orElseThrow(() -> new ResourceNotFoundException("Department not found"));
    }

    private void validateCompany(String companyId) {
        companyRepository.findByCompanyIdAndDeletedAtIsNull(companyId)
                .filter(company -> Boolean.TRUE.equals(company.getIsActive()))
                .orElseThrow(() -> new ResourceNotFoundException("Company not found"));
    }
}
