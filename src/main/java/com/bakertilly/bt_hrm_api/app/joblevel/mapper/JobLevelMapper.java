package com.bakertilly.bt_hrm_api.app.joblevel.mapper;

import com.bakertilly.bt_hrm_api.app.joblevel.dto.JobLevelRequest;
import com.bakertilly.bt_hrm_api.app.joblevel.dto.JobLevelResponse;
import com.bakertilly.bt_hrm_api.app.joblevel.entity.JobLevel;
import org.springframework.stereotype.Component;

@Component
public class JobLevelMapper {
    public void updateEntity(JobLevel entity, JobLevelRequest request) {
        entity.setCompanyId(request.getCompanyId());
        entity.setJobLevelCode(request.getJobLevelCode());
        entity.setJobLevelName(request.getJobLevelName());
        entity.setJobLevelNameLocal(request.getJobLevelNameLocal());
        entity.setJobLevelGroup(request.getJobLevelGroup());
        entity.setLevelRank(request.getLevelRank());
        entity.setSortOrder(request.getSortOrder() == null ? 0 : request.getSortOrder());
        entity.setDescription(request.getDescription());
    }
    public JobLevelResponse toResponse(JobLevel entity) {
        return JobLevelResponse.builder().jobLevelId(entity.getJobLevelId()).companyId(entity.getCompanyId())
                .jobLevelCode(entity.getJobLevelCode()).jobLevelName(entity.getJobLevelName())
                .jobLevelNameLocal(entity.getJobLevelNameLocal()).jobLevelGroup(entity.getJobLevelGroup())
                .levelRank(entity.getLevelRank()).sortOrder(entity.getSortOrder()).description(entity.getDescription())
                .isActive(entity.getIsActive()).createdAt(entity.getCreatedAt()).updatedAt(entity.getUpdatedAt()).build();
    }
}
