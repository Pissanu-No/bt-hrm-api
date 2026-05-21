package com.bakertilly.bt_hrm_api.app.location.service.impl;

import com.bakertilly.bt_hrm_api.app.common.exception.DuplicateResourceException;
import com.bakertilly.bt_hrm_api.app.common.exception.ResourceNotFoundException;
import com.bakertilly.bt_hrm_api.app.common.response.PagedResponse;
import com.bakertilly.bt_hrm_api.app.location.dto.LocationRequest;
import com.bakertilly.bt_hrm_api.app.location.dto.LocationResponse;
import com.bakertilly.bt_hrm_api.app.location.entity.Location;
import com.bakertilly.bt_hrm_api.app.location.mapper.LocationMapper;
import com.bakertilly.bt_hrm_api.app.location.repository.LocationRepository;
import com.bakertilly.bt_hrm_api.app.location.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {
    private final LocationRepository repository;
    private final LocationMapper mapper;
    @Override @Transactional(readOnly = true)
    public PagedResponse<LocationResponse> findAll(Pageable pageable) {
        return PagedResponse.from(repository.findByIsActiveTrueAndDeletedAtIsNull(pageable).map(mapper::toResponse));
    }
    @Override @Transactional(readOnly = true)
    public LocationResponse findById(String locationId) { return mapper.toResponse(findActive(locationId)); }
    @Override @Transactional
    public LocationResponse create(LocationRequest request) {
        if (repository.existsByLocationCodeAndDeletedAtIsNull(request.getLocationCode())) throw new DuplicateResourceException("Location code already exists");
        Location entity = new Location(); entity.setLocationId(UUID.randomUUID().toString()); mapper.updateEntity(entity, request);
        return mapper.toResponse(repository.save(entity));
    }
    @Override @Transactional
    public LocationResponse update(String locationId, LocationRequest request) {
        Location entity = findActive(locationId);
        if (repository.existsByLocationCodeAndLocationIdNotAndDeletedAtIsNull(request.getLocationCode(), locationId)) throw new DuplicateResourceException("Location code already exists");
        mapper.updateEntity(entity, request); return mapper.toResponse(repository.save(entity));
    }
    @Override @Transactional
    public void delete(String locationId) { Location entity = findActive(locationId); entity.softDelete(); repository.save(entity); }
    private Location findActive(String locationId) {
        return repository.findByLocationIdAndDeletedAtIsNull(locationId).filter(entity -> Boolean.TRUE.equals(entity.getIsActive()))
                .orElseThrow(() -> new ResourceNotFoundException("Location not found"));
    }
}
