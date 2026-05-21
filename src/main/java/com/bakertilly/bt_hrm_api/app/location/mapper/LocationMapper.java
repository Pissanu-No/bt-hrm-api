package com.bakertilly.bt_hrm_api.app.location.mapper;

import com.bakertilly.bt_hrm_api.app.location.dto.LocationRequest;
import com.bakertilly.bt_hrm_api.app.location.dto.LocationResponse;
import com.bakertilly.bt_hrm_api.app.location.entity.Location;
import org.springframework.stereotype.Component;

@Component
public class LocationMapper {
    public void updateEntity(Location entity, LocationRequest request) {
        entity.setCompanyId(request.getCompanyId());
        entity.setBranchId(request.getBranchId());
        entity.setLocationCode(request.getLocationCode());
        entity.setLocationName(request.getLocationName());
        entity.setLocationNameLocal(request.getLocationNameLocal());
        entity.setLocationType(request.getLocationType());
        entity.setBuildingName(request.getBuildingName());
        entity.setFloorNo(request.getFloorNo());
        entity.setRoomNo(request.getRoomNo());
        entity.setAddressLine1(request.getAddressLine1());
        entity.setAddressLine2(request.getAddressLine2());
        entity.setProvince(request.getProvince());
        entity.setCountry(request.getCountry());
        entity.setPostalCode(request.getPostalCode());
        entity.setSortOrder(request.getSortOrder() == null ? 0 : request.getSortOrder());
        entity.setDescription(request.getDescription());
    }
    public LocationResponse toResponse(Location entity) {
        return LocationResponse.builder()
                .locationId(entity.getLocationId()).companyId(entity.getCompanyId()).branchId(entity.getBranchId())
                .locationCode(entity.getLocationCode()).locationName(entity.getLocationName())
                .locationNameLocal(entity.getLocationNameLocal()).locationType(entity.getLocationType())
                .buildingName(entity.getBuildingName()).floorNo(entity.getFloorNo()).roomNo(entity.getRoomNo())
                .addressLine1(entity.getAddressLine1()).addressLine2(entity.getAddressLine2())
                .province(entity.getProvince()).country(entity.getCountry()).postalCode(entity.getPostalCode())
                .sortOrder(entity.getSortOrder()).description(entity.getDescription()).isActive(entity.getIsActive())
                .createdAt(entity.getCreatedAt()).updatedAt(entity.getUpdatedAt()).build();
    }
}
