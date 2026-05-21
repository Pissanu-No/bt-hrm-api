package com.bakertilly.bt_hrm_api.app.employee.address.mapper;

import com.bakertilly.bt_hrm_api.app.employee.address.dto.EmployeeAddressCreateRequest;
import com.bakertilly.bt_hrm_api.app.employee.address.entity.EmployeeAddress;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EmployeeAddressMapperTest {
    private final EmployeeAddressMapper mapper = new EmployeeAddressMapper();

    @Test
    void updateEntityDefaultsCountryAndBooleanFields() {
        EmployeeAddressCreateRequest request = new EmployeeAddressCreateRequest();
        request.setAddressType("REGISTERED_ADDRESS");

        EmployeeAddress entity = new EmployeeAddress();
        mapper.updateEntity(entity, request);

        assertThat(entity.getCountry()).isEqualTo("Thailand");
        assertThat(entity.getIsPrimary()).isFalse();
        assertThat(entity.getIsSameAsRegisteredAddress()).isFalse();
    }
}
