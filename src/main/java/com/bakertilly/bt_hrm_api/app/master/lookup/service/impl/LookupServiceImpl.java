package com.bakertilly.bt_hrm_api.app.master.lookup.service.impl;

import com.bakertilly.bt_hrm_api.app.common.exception.DuplicateResourceException;
import com.bakertilly.bt_hrm_api.app.common.exception.ResourceNotFoundException;
import com.bakertilly.bt_hrm_api.app.common.response.PagedResponse;
import com.bakertilly.bt_hrm_api.app.master.lookup.dto.*;
import com.bakertilly.bt_hrm_api.app.master.lookup.entity.LookupGroup;
import com.bakertilly.bt_hrm_api.app.master.lookup.entity.LookupValue;
import com.bakertilly.bt_hrm_api.app.master.lookup.mapper.LookupMapper;
import com.bakertilly.bt_hrm_api.app.master.lookup.repository.LookupGroupRepository;
import com.bakertilly.bt_hrm_api.app.master.lookup.repository.LookupValueRepository;
import com.bakertilly.bt_hrm_api.app.master.lookup.service.LookupService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LookupServiceImpl implements LookupService {
    private final LookupGroupRepository groupRepository;
    private final LookupValueRepository valueRepository;
    private final LookupMapper mapper;

    @Override @Transactional(readOnly = true)
    public PagedResponse<LookupGroupResponse> findGroups(Pageable pageable) {
        return PagedResponse.from(groupRepository.findByIsActiveTrueAndDeletedAtIsNull(pageable).map(mapper::toGroupResponse));
    }
    @Override @Transactional(readOnly = true)
    public LookupGroupResponse findGroupById(String lookupGroupId) { return mapper.toGroupResponse(findGroup(lookupGroupId)); }
    @Override @Transactional
    public LookupGroupResponse createGroup(LookupGroupRequest request) {
        if (groupRepository.existsByLookupGroupCodeAndDeletedAtIsNull(request.getLookupGroupCode())) throw new DuplicateResourceException("Lookup group code already exists");
        LookupGroup entity = new LookupGroup(); entity.setLookupGroupId(UUID.randomUUID().toString()); mapper.updateGroup(entity, request);
        return mapper.toGroupResponse(groupRepository.save(entity));
    }
    @Override @Transactional
    public LookupGroupResponse updateGroup(String lookupGroupId, LookupGroupRequest request) {
        LookupGroup entity = findGroup(lookupGroupId);
        if (groupRepository.existsByLookupGroupCodeAndLookupGroupIdNotAndDeletedAtIsNull(request.getLookupGroupCode(), lookupGroupId)) throw new DuplicateResourceException("Lookup group code already exists");
        mapper.updateGroup(entity, request); return mapper.toGroupResponse(groupRepository.save(entity));
    }
    @Override @Transactional
    public void deleteGroup(String lookupGroupId) {
        LookupGroup entity = findGroup(lookupGroupId);
        if (Boolean.TRUE.equals(entity.getIsSystem())) throw new IllegalArgumentException("System lookup group cannot be deleted");
        entity.softDelete(); groupRepository.save(entity);
    }
    @Override @Transactional(readOnly = true)
    public PagedResponse<LookupValueResponse> findValues(Pageable pageable) {
        return PagedResponse.from(valueRepository.findByIsActiveTrueAndDeletedAtIsNull(pageable).map(mapper::toValueResponse));
    }
    @Override @Transactional(readOnly = true)
    public LookupValueResponse findValueById(String lookupValueId) { return mapper.toValueResponse(findValue(lookupValueId)); }
    @Override @Transactional(readOnly = true)
    public List<LookupValueResponse> findValuesByGroupCode(String lookupGroupCode) {
        LookupGroup group = groupRepository.findByLookupGroupCodeAndDeletedAtIsNull(lookupGroupCode)
                .filter(entity -> Boolean.TRUE.equals(entity.getIsActive()))
                .orElseThrow(() -> new ResourceNotFoundException("Lookup group not found"));
        return valueRepository.findByLookupGroupIdAndIsActiveTrueAndDeletedAtIsNullOrderBySortOrderAscLookupCodeAsc(group.getLookupGroupId())
                .stream().map(mapper::toValueResponse).toList();
    }
    @Override @Transactional
    public LookupValueResponse createValue(LookupValueRequest request) {
        findGroup(request.getLookupGroupId());
        if (valueRepository.existsByLookupGroupIdAndLookupCodeAndDeletedAtIsNull(request.getLookupGroupId(), request.getLookupCode())) throw new DuplicateResourceException("Lookup code already exists in lookup group");
        LookupValue entity = new LookupValue(); entity.setLookupValueId(UUID.randomUUID().toString()); mapper.updateValue(entity, request);
        return mapper.toValueResponse(valueRepository.save(entity));
    }
    @Override @Transactional
    public LookupValueResponse updateValue(String lookupValueId, LookupValueRequest request) {
        findGroup(request.getLookupGroupId());
        LookupValue entity = findValue(lookupValueId);
        if (valueRepository.existsByLookupGroupIdAndLookupCodeAndLookupValueIdNotAndDeletedAtIsNull(request.getLookupGroupId(), request.getLookupCode(), lookupValueId)) throw new DuplicateResourceException("Lookup code already exists in lookup group");
        mapper.updateValue(entity, request); return mapper.toValueResponse(valueRepository.save(entity));
    }
    @Override @Transactional
    public void deleteValue(String lookupValueId) {
        LookupValue entity = findValue(lookupValueId);
        if (Boolean.TRUE.equals(entity.getIsSystem())) throw new IllegalArgumentException("System lookup value cannot be deleted");
        entity.softDelete(); valueRepository.save(entity);
    }
    private LookupGroup findGroup(String lookupGroupId) {
        return groupRepository.findByLookupGroupIdAndDeletedAtIsNull(lookupGroupId).filter(entity -> Boolean.TRUE.equals(entity.getIsActive()))
                .orElseThrow(() -> new ResourceNotFoundException("Lookup group not found"));
    }
    private LookupValue findValue(String lookupValueId) {
        return valueRepository.findByLookupValueIdAndDeletedAtIsNull(lookupValueId).filter(entity -> Boolean.TRUE.equals(entity.getIsActive()))
                .orElseThrow(() -> new ResourceNotFoundException("Lookup value not found"));
    }
}
