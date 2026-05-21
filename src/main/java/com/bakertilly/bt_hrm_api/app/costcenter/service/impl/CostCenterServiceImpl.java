package com.bakertilly.bt_hrm_api.app.costcenter.service.impl;

import com.bakertilly.bt_hrm_api.app.common.exception.DuplicateResourceException;
import com.bakertilly.bt_hrm_api.app.common.exception.ResourceNotFoundException;
import com.bakertilly.bt_hrm_api.app.common.response.PagedResponse;
import com.bakertilly.bt_hrm_api.app.costcenter.dto.CostCenterRequest;
import com.bakertilly.bt_hrm_api.app.costcenter.dto.CostCenterResponse;
import com.bakertilly.bt_hrm_api.app.costcenter.entity.CostCenter;
import com.bakertilly.bt_hrm_api.app.costcenter.mapper.CostCenterMapper;
import com.bakertilly.bt_hrm_api.app.costcenter.repository.CostCenterRepository;
import com.bakertilly.bt_hrm_api.app.costcenter.service.CostCenterService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CostCenterServiceImpl implements CostCenterService {
    private final CostCenterRepository repository;
    private final CostCenterMapper mapper;
    @Override @Transactional(readOnly = true)
    public PagedResponse<CostCenterResponse> findAll(Pageable pageable) { return PagedResponse.from(repository.findByIsActiveTrueAndDeletedAtIsNull(pageable).map(mapper::toResponse)); }
    @Override @Transactional(readOnly = true)
    public CostCenterResponse findById(String costCenterId) { return mapper.toResponse(findActive(costCenterId)); }
    @Override @Transactional
    public CostCenterResponse create(CostCenterRequest request) {
        if (repository.existsByCostCenterCodeAndDeletedAtIsNull(request.getCostCenterCode())) throw new DuplicateResourceException("Cost center code already exists");
        CostCenter entity = new CostCenter(); entity.setCostCenterId(UUID.randomUUID().toString()); mapper.updateEntity(entity, request); return mapper.toResponse(repository.save(entity));
    }
    @Override @Transactional
    public CostCenterResponse update(String costCenterId, CostCenterRequest request) {
        CostCenter entity = findActive(costCenterId);
        if (repository.existsByCostCenterCodeAndCostCenterIdNotAndDeletedAtIsNull(request.getCostCenterCode(), costCenterId)) throw new DuplicateResourceException("Cost center code already exists");
        mapper.updateEntity(entity, request); return mapper.toResponse(repository.save(entity));
    }
    @Override @Transactional
    public void delete(String costCenterId) { CostCenter entity = findActive(costCenterId); entity.softDelete(); repository.save(entity); }
    private CostCenter findActive(String costCenterId) {
        return repository.findByCostCenterIdAndDeletedAtIsNull(costCenterId).filter(entity -> Boolean.TRUE.equals(entity.getIsActive()))
                .orElseThrow(() -> new ResourceNotFoundException("Cost center not found"));
    }
}
