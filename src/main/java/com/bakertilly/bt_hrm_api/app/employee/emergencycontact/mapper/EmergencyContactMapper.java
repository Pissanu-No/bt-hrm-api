package com.bakertilly.bt_hrm_api.app.employee.emergencycontact.mapper;

import com.bakertilly.bt_hrm_api.app.employee.emergencycontact.dto.EmergencyContactRequest;
import com.bakertilly.bt_hrm_api.app.employee.emergencycontact.dto.EmergencyContactResponse;
import com.bakertilly.bt_hrm_api.app.employee.emergencycontact.entity.EmergencyContact;
import org.springframework.stereotype.Component;

@Component
public class EmergencyContactMapper {
    public void updateEntity(EmergencyContact entity, EmergencyContactRequest request) {
        entity.setContactName(request.getContactName());
        entity.setRelationship(request.getRelationship());
        entity.setMobilePhone(request.getMobilePhone());
        entity.setAlternatePhone(request.getAlternatePhone());
        entity.setEmail(request.getEmail());
        entity.setAddressLine1(request.getAddressLine1());
        entity.setAddressLine2(request.getAddressLine2());
        entity.setProvince(request.getProvince());
        entity.setCountry(request.getCountry());
        entity.setPostalCode(request.getPostalCode());
        entity.setPriorityNo(request.getPriorityNo() == null ? 1 : request.getPriorityNo());
        entity.setIsPrimary(Boolean.TRUE.equals(request.getIsPrimary()));
        entity.setNote(request.getNote());
    }

    public EmergencyContactResponse toResponse(EmergencyContact entity) {
        return EmergencyContactResponse.builder()
                .emergencyContactId(entity.getEmergencyContactId())
                .employeeId(entity.getEmployeeId())
                .contactName(entity.getContactName())
                .relationship(entity.getRelationship())
                .mobilePhone(entity.getMobilePhone())
                .alternatePhone(entity.getAlternatePhone())
                .email(entity.getEmail())
                .addressLine1(entity.getAddressLine1())
                .addressLine2(entity.getAddressLine2())
                .province(entity.getProvince())
                .country(entity.getCountry())
                .postalCode(entity.getPostalCode())
                .priorityNo(entity.getPriorityNo())
                .isPrimary(entity.getIsPrimary())
                .note(entity.getNote())
                .isActive(entity.getIsActive())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
