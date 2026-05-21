package com.bakertilly.bt_hrm_api.app.position.service.impl;

import com.bakertilly.bt_hrm_api.app.common.exception.DuplicateResourceException;
import com.bakertilly.bt_hrm_api.app.common.exception.ResourceNotFoundException;
import com.bakertilly.bt_hrm_api.app.common.response.PagedResponse;
import com.bakertilly.bt_hrm_api.app.company.repository.CompanyRepository;
import com.bakertilly.bt_hrm_api.app.department.repository.DepartmentRepository;
import com.bakertilly.bt_hrm_api.app.position.dto.PositionRequest;
import com.bakertilly.bt_hrm_api.app.position.dto.PositionResponse;
import com.bakertilly.bt_hrm_api.app.position.entity.Position;
import com.bakertilly.bt_hrm_api.app.position.mapper.PositionMapper;
import com.bakertilly.bt_hrm_api.app.position.repository.PositionRepository;
import com.bakertilly.bt_hrm_api.app.position.service.PositionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PositionServiceImpl implements PositionService {
    private final PositionRepository positionRepository;
    private final CompanyRepository companyRepository;
    private final DepartmentRepository departmentRepository;
    private final PositionMapper positionMapper;

    @Override
    @Transactional(readOnly = true)
    public PagedResponse<PositionResponse> findAll(Pageable pageable) {
        return PagedResponse.from(positionRepository.findByIsActiveTrueAndDeletedAtIsNull(pageable)
                .map(positionMapper::toResponse));
    }

    @Override
    @Transactional(readOnly = true)
    public PositionResponse findById(String positionId) {
        return positionMapper.toResponse(findActivePosition(positionId));
    }

    @Override
    @Transactional
    public PositionResponse create(PositionRequest request) {
        validateReferences(request);
        if (positionRepository.existsByCompanyIdAndPositionCodeAndDeletedAtIsNull(
                request.getCompanyId(), request.getPositionCode())) {
            throw new DuplicateResourceException("Position code already exists for this company");
        }

        Position position = new Position();
        position.setPositionId(UUID.randomUUID().toString());
        positionMapper.updateEntity(position, request);
        return positionMapper.toResponse(positionRepository.save(position));
    }

    @Override
    @Transactional
    public PositionResponse update(String positionId, PositionRequest request) {
        Position position = findActivePosition(positionId);
        validateReferences(request);
        if (positionRepository.existsByCompanyIdAndPositionCodeAndPositionIdNotAndDeletedAtIsNull(
                request.getCompanyId(), request.getPositionCode(), positionId)) {
            throw new DuplicateResourceException("Position code already exists for this company");
        }

        positionMapper.updateEntity(position, request);
        return positionMapper.toResponse(positionRepository.save(position));
    }

    @Override
    @Transactional
    public void delete(String positionId) {
        Position position = findActivePosition(positionId);
        position.softDelete();
        positionRepository.save(position);
    }

    private Position findActivePosition(String positionId) {
        return positionRepository.findByPositionIdAndDeletedAtIsNull(positionId)
                .filter(position -> Boolean.TRUE.equals(position.getIsActive()))
                .orElseThrow(() -> new ResourceNotFoundException("Position not found"));
    }

    private void validateReferences(PositionRequest request) {
        companyRepository.findByCompanyIdAndDeletedAtIsNull(request.getCompanyId())
                .filter(company -> Boolean.TRUE.equals(company.getIsActive()))
                .orElseThrow(() -> new ResourceNotFoundException("Company not found"));

        if (StringUtils.hasText(request.getDepartmentId())) {
            departmentRepository.findByDepartmentIdAndDeletedAtIsNull(request.getDepartmentId())
                    .filter(department -> Boolean.TRUE.equals(department.getIsActive()))
                    .orElseThrow(() -> new ResourceNotFoundException("Department not found"));
        }
    }
}
