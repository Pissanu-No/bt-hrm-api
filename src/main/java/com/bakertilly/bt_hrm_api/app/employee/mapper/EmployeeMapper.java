package com.bakertilly.bt_hrm_api.app.employee.mapper;

import com.bakertilly.bt_hrm_api.app.employee.dto.EmployeeListResponse;
import com.bakertilly.bt_hrm_api.app.employee.dto.EmployeeRequest;
import com.bakertilly.bt_hrm_api.app.employee.dto.EmployeeResponse;
import com.bakertilly.bt_hrm_api.app.employee.entity.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {
    public void updateEntity(Employee entity, EmployeeRequest request) {
        entity.setEmployeeCode(request.getEmployeeCode());
        entity.setTitleName(request.getTitleName());
        entity.setFirstName(request.getFirstName());
        entity.setMiddleName(request.getMiddleName());
        entity.setLastName(request.getLastName());
        entity.setTitleNameLocal(request.getTitleNameLocal());
        entity.setFirstNameLocal(request.getFirstNameLocal());
        entity.setMiddleNameLocal(request.getMiddleNameLocal());
        entity.setLastNameLocal(request.getLastNameLocal());
        entity.setPreferredName(request.getPreferredName());
        entity.setGender(request.getGender());
        entity.setBirthDate(request.getBirthDate());
        entity.setNationality(request.getNationality());
        entity.setAddress(request.getAddress());
        entity.setMaritalStatus(request.getMaritalStatus());
        entity.setBloodType(request.getBloodType());
        entity.setNationalId(request.getNationalId());
        entity.setPassportNo(request.getPassportNo());
        entity.setTaxNo(request.getTaxNo());
        entity.setSocialSecurityNo(request.getSocialSecurityNo());
        entity.setHighestEducationLevel(request.getHighestEducationLevel());
        entity.setRegisteredAddressId(request.getRegisteredAddressId());
        entity.setContactAddressId(request.getContactAddressId());
        entity.setPersonalEmail(request.getPersonalEmail());
        entity.setWorkEmail(request.getWorkEmail());
        entity.setMobilePhone(request.getMobilePhone());
        entity.setEmployeeStatus(request.getEmployeeStatus());
        entity.setProfileImageUrl(request.getProfileImageUrl());
    }

    public EmployeeResponse toResponse(Employee entity) {
        return EmployeeResponse.builder()
                .employeeId(entity.getEmployeeId())
                .employeeCode(entity.getEmployeeCode())
                .titleName(entity.getTitleName())
                .firstName(entity.getFirstName())
                .middleName(entity.getMiddleName())
                .lastName(entity.getLastName())
                .titleNameLocal(entity.getTitleNameLocal())
                .firstNameLocal(entity.getFirstNameLocal())
                .middleNameLocal(entity.getMiddleNameLocal())
                .lastNameLocal(entity.getLastNameLocal())
                .preferredName(entity.getPreferredName())
                .gender(entity.getGender())
                .birthDate(entity.getBirthDate())
                .nationality(entity.getNationality())
                .address(entity.getAddress())
                .maritalStatus(entity.getMaritalStatus())
                .bloodType(entity.getBloodType())
                .nationalId(entity.getNationalId())
                .passportNo(entity.getPassportNo())
                .taxNo(entity.getTaxNo())
                .socialSecurityNo(entity.getSocialSecurityNo())
                .highestEducationLevel(entity.getHighestEducationLevel())
                .registeredAddressId(entity.getRegisteredAddressId())
                .contactAddressId(entity.getContactAddressId())
                .personalEmail(entity.getPersonalEmail())
                .workEmail(entity.getWorkEmail())
                .mobilePhone(entity.getMobilePhone())
                .employeeStatus(entity.getEmployeeStatus())
                .profileImageUrl(entity.getProfileImageUrl())
                .isActive(entity.getIsActive())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    public EmployeeListResponse toListResponse(Employee entity) {
        return EmployeeListResponse.builder()
                .employeeId(entity.getEmployeeId())
                .employeeCode(entity.getEmployeeCode())
                .titleName(entity.getTitleName())
                .firstName(entity.getFirstName())
                .middleName(entity.getMiddleName())
                .lastName(entity.getLastName())
                .preferredName(entity.getPreferredName())
                .workEmail(entity.getWorkEmail())
                .mobilePhone(entity.getMobilePhone())
                .employeeStatus(entity.getEmployeeStatus())
                .isActive(entity.getIsActive())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
