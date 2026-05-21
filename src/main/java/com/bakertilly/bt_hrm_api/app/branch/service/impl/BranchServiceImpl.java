package com.bakertilly.bt_hrm_api.app.branch.service.impl;

import com.bakertilly.bt_hrm_api.app.branch.dto.BranchRequest;
import com.bakertilly.bt_hrm_api.app.branch.dto.BranchResponse;
import com.bakertilly.bt_hrm_api.app.branch.entity.Branch;
import com.bakertilly.bt_hrm_api.app.branch.mapper.BranchMapper;
import com.bakertilly.bt_hrm_api.app.branch.repository.BranchRepository;
import com.bakertilly.bt_hrm_api.app.branch.service.BranchService;
import com.bakertilly.bt_hrm_api.app.common.exception.DuplicateResourceException;
import com.bakertilly.bt_hrm_api.app.common.exception.ResourceNotFoundException;
import com.bakertilly.bt_hrm_api.app.common.response.PagedResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BranchServiceImpl implements BranchService {
    private final BranchRepository repository;
    private final BranchMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public PagedResponse<BranchResponse> findAll(Pageable pageable) {
        return PagedResponse.from(repository.findByIsActiveTrueAndDeletedAtIsNull(pageable).map(mapper::toResponse));
    }

    @Override
    @Transactional(readOnly = true)
    public BranchResponse findById(String branchId) {
        return mapper.toResponse(findActive(branchId));
    }

    @Override
    @Transactional
    public BranchResponse create(BranchRequest request) {
        if (repository.existsByBranchCodeAndDeletedAtIsNull(request.getBranchCode())) {
            throw new DuplicateResourceException("Branch code already exists");
        }
        Branch entity = new Branch();
        entity.setBranchId(UUID.randomUUID().toString());
        mapper.updateEntity(entity, request);
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    @Transactional
    public BranchResponse update(String branchId, BranchRequest request) {
        Branch entity = findActive(branchId);
        if (repository.existsByBranchCodeAndBranchIdNotAndDeletedAtIsNull(request.getBranchCode(), branchId)) {
            throw new DuplicateResourceException("Branch code already exists");
        }
        mapper.updateEntity(entity, request);
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    @Transactional
    public void delete(String branchId) {
        Branch entity = findActive(branchId);
        entity.softDelete();
        repository.save(entity);
    }

    private Branch findActive(String branchId) {
        return repository.findByBranchIdAndDeletedAtIsNull(branchId)
                .filter(entity -> Boolean.TRUE.equals(entity.getIsActive()))
                .orElseThrow(() -> new ResourceNotFoundException("Branch not found"));
    }
}
