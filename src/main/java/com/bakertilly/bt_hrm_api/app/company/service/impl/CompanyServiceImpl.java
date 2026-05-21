package com.bakertilly.bt_hrm_api.app.company.service.impl;

import com.bakertilly.bt_hrm_api.app.common.exception.DuplicateResourceException;
import com.bakertilly.bt_hrm_api.app.common.exception.ResourceNotFoundException;
import com.bakertilly.bt_hrm_api.app.common.response.PagedResponse;
import com.bakertilly.bt_hrm_api.app.company.dto.CompanyRequest;
import com.bakertilly.bt_hrm_api.app.company.dto.CompanyResponse;
import com.bakertilly.bt_hrm_api.app.company.entity.Company;
import com.bakertilly.bt_hrm_api.app.company.mapper.CompanyMapper;
import com.bakertilly.bt_hrm_api.app.company.repository.CompanyRepository;
import com.bakertilly.bt_hrm_api.app.company.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;

    @Override
    @Transactional(readOnly = true)
    public PagedResponse<CompanyResponse> findAll(Pageable pageable) {
        return PagedResponse.from(companyRepository.findByIsActiveTrueAndDeletedAtIsNull(pageable)
                .map(companyMapper::toResponse));
    }

    @Override
    @Transactional(readOnly = true)
    public CompanyResponse findById(String companyId) {
        return companyMapper.toResponse(findActiveCompany(companyId));
    }

    @Override
    @Transactional
    public CompanyResponse create(CompanyRequest request) {
        if (companyRepository.existsByCompanyCodeAndDeletedAtIsNull(request.getCompanyCode())) {
            throw new DuplicateResourceException("Company code already exists");
        }

        Company company = new Company();
        company.setCompanyId(UUID.randomUUID().toString());
        companyMapper.updateEntity(company, request);
        return companyMapper.toResponse(companyRepository.save(company));
    }

    @Override
    @Transactional
    public CompanyResponse update(String companyId, CompanyRequest request) {
        Company company = findActiveCompany(companyId);
        if (companyRepository.existsByCompanyCodeAndCompanyIdNotAndDeletedAtIsNull(
                request.getCompanyCode(), companyId)) {
            throw new DuplicateResourceException("Company code already exists");
        }

        companyMapper.updateEntity(company, request);
        return companyMapper.toResponse(companyRepository.save(company));
    }

    @Override
    @Transactional
    public void delete(String companyId) {
        Company company = findActiveCompany(companyId);
        company.softDelete();
        companyRepository.save(company);
    }

    private Company findActiveCompany(String companyId) {
        return companyRepository.findByCompanyIdAndDeletedAtIsNull(companyId)
                .filter(company -> Boolean.TRUE.equals(company.getIsActive()))
                .orElseThrow(() -> new ResourceNotFoundException("Company not found"));
    }
}
