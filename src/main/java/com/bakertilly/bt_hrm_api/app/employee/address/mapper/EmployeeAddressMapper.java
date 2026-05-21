package com.bakertilly.bt_hrm_api.app.employee.address.mapper;

import com.bakertilly.bt_hrm_api.app.employee.address.dto.EmployeeAddressCreateRequest;
import com.bakertilly.bt_hrm_api.app.employee.address.dto.EmployeeAddressResponse;
import com.bakertilly.bt_hrm_api.app.employee.address.entity.EmployeeAddress;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class EmployeeAddressMapper {
    public void updateEntity(EmployeeAddress entity, EmployeeAddressCreateRequest request) {
        entity.setAddressType(request.getAddressType());
        entity.setAddressLabel(request.getAddressLabel());
        entity.setHouseNo(request.getHouseNo());
        entity.setVillageNo(request.getVillageNo());
        entity.setVillageName(request.getVillageName());
        entity.setBuildingName(request.getBuildingName());
        entity.setFloorNo(request.getFloorNo());
        entity.setRoomNo(request.getRoomNo());
        entity.setAlley(request.getAlley());
        entity.setRoad(request.getRoad());
        entity.setSubDistrict(request.getSubDistrict());
        entity.setDistrict(request.getDistrict());
        entity.setProvince(request.getProvince());
        entity.setPostalCode(request.getPostalCode());
        entity.setCountry(StringUtils.hasText(request.getCountry()) ? request.getCountry() : "Thailand");
        entity.setFullAddress(request.getFullAddress());
        entity.setLatitude(request.getLatitude());
        entity.setLongitude(request.getLongitude());
        entity.setIsPrimary(request.getIsPrimary() != null && request.getIsPrimary());
        entity.setIsSameAsRegisteredAddress(request.getIsSameAsRegisteredAddress() != null
                && request.getIsSameAsRegisteredAddress());
        entity.setEffectiveStartDate(request.getEffectiveStartDate());
        entity.setEffectiveEndDate(request.getEffectiveEndDate());
        entity.setNote(request.getNote());
    }

    public EmployeeAddressResponse toResponse(EmployeeAddress entity) {
        return EmployeeAddressResponse.builder()
                .addressId(entity.getAddressId())
                .employeeId(entity.getEmployeeId())
                .addressType(entity.getAddressType())
                .addressLabel(entity.getAddressLabel())
                .houseNo(entity.getHouseNo())
                .villageNo(entity.getVillageNo())
                .villageName(entity.getVillageName())
                .buildingName(entity.getBuildingName())
                .floorNo(entity.getFloorNo())
                .roomNo(entity.getRoomNo())
                .alley(entity.getAlley())
                .road(entity.getRoad())
                .subDistrict(entity.getSubDistrict())
                .district(entity.getDistrict())
                .province(entity.getProvince())
                .postalCode(entity.getPostalCode())
                .country(entity.getCountry())
                .fullAddress(entity.getFullAddress())
                .latitude(entity.getLatitude())
                .longitude(entity.getLongitude())
                .isPrimary(entity.getIsPrimary())
                .isSameAsRegisteredAddress(entity.getIsSameAsRegisteredAddress())
                .effectiveStartDate(entity.getEffectiveStartDate())
                .effectiveEndDate(entity.getEffectiveEndDate())
                .note(entity.getNote())
                .isActive(entity.getIsActive())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
