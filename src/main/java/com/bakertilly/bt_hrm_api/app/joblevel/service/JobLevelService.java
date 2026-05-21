package com.bakertilly.bt_hrm_api.app.joblevel.service;

import com.bakertilly.bt_hrm_api.app.common.response.PagedResponse;
import com.bakertilly.bt_hrm_api.app.joblevel.dto.JobLevelRequest;
import com.bakertilly.bt_hrm_api.app.joblevel.dto.JobLevelResponse;
import org.springframework.data.domain.Pageable;

public interface JobLevelService {
    PagedResponse<JobLevelResponse> findAll(Pageable pageable);
    JobLevelResponse findById(String jobLevelId);
    JobLevelResponse create(JobLevelRequest request);
    JobLevelResponse update(String jobLevelId, JobLevelRequest request);
    void delete(String jobLevelId);
}
