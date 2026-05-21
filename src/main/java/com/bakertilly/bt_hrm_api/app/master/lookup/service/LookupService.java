package com.bakertilly.bt_hrm_api.app.master.lookup.service;

import com.bakertilly.bt_hrm_api.app.common.response.PagedResponse;
import com.bakertilly.bt_hrm_api.app.master.lookup.dto.*;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LookupService {
    PagedResponse<LookupGroupResponse> findGroups(Pageable pageable);
    LookupGroupResponse findGroupById(String lookupGroupId);
    LookupGroupResponse createGroup(LookupGroupRequest request);
    LookupGroupResponse updateGroup(String lookupGroupId, LookupGroupRequest request);
    void deleteGroup(String lookupGroupId);
    PagedResponse<LookupValueResponse> findValues(Pageable pageable);
    LookupValueResponse findValueById(String lookupValueId);
    List<LookupValueResponse> findValuesByGroupCode(String lookupGroupCode);
    LookupValueResponse createValue(LookupValueRequest request);
    LookupValueResponse updateValue(String lookupValueId, LookupValueRequest request);
    void deleteValue(String lookupValueId);
}
