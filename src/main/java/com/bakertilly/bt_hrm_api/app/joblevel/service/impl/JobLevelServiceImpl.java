package com.bakertilly.bt_hrm_api.app.joblevel.service.impl;

import com.bakertilly.bt_hrm_api.app.common.exception.DuplicateResourceException;
import com.bakertilly.bt_hrm_api.app.common.exception.ResourceNotFoundException;
import com.bakertilly.bt_hrm_api.app.common.response.PagedResponse;
import com.bakertilly.bt_hrm_api.app.joblevel.dto.JobLevelRequest;
import com.bakertilly.bt_hrm_api.app.joblevel.dto.JobLevelResponse;
import com.bakertilly.bt_hrm_api.app.joblevel.entity.JobLevel;
import com.bakertilly.bt_hrm_api.app.joblevel.mapper.JobLevelMapper;
import com.bakertilly.bt_hrm_api.app.joblevel.repository.JobLevelRepository;
import com.bakertilly.bt_hrm_api.app.joblevel.service.JobLevelService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JobLevelServiceImpl implements JobLevelService {
    private final JobLevelRepository repository;
    private final JobLevelMapper mapper;
    @Override @Transactional(readOnly = true)
    public PagedResponse<JobLevelResponse> findAll(Pageable pageable) { return PagedResponse.from(repository.findByIsActiveTrueAndDeletedAtIsNull(pageable).map(mapper::toResponse)); }
    @Override @Transactional(readOnly = true)
    public JobLevelResponse findById(String jobLevelId) { return mapper.toResponse(findActive(jobLevelId)); }
    @Override @Transactional
    public JobLevelResponse create(JobLevelRequest request) {
        if (repository.existsByJobLevelCodeAndDeletedAtIsNull(request.getJobLevelCode())) throw new DuplicateResourceException("Job level code already exists");
        JobLevel entity = new JobLevel(); entity.setJobLevelId(UUID.randomUUID().toString()); mapper.updateEntity(entity, request); return mapper.toResponse(repository.save(entity));
    }
    @Override @Transactional
    public JobLevelResponse update(String jobLevelId, JobLevelRequest request) {
        JobLevel entity = findActive(jobLevelId);
        if (repository.existsByJobLevelCodeAndJobLevelIdNotAndDeletedAtIsNull(request.getJobLevelCode(), jobLevelId)) throw new DuplicateResourceException("Job level code already exists");
        mapper.updateEntity(entity, request); return mapper.toResponse(repository.save(entity));
    }
    @Override @Transactional
    public void delete(String jobLevelId) { JobLevel entity = findActive(jobLevelId); entity.softDelete(); repository.save(entity); }
    private JobLevel findActive(String jobLevelId) {
        return repository.findByJobLevelIdAndDeletedAtIsNull(jobLevelId).filter(entity -> Boolean.TRUE.equals(entity.getIsActive()))
                .orElseThrow(() -> new ResourceNotFoundException("Job level not found"));
    }
}
