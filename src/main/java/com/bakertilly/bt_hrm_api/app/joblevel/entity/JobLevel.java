package com.bakertilly.bt_hrm_api.app.joblevel.entity;

import com.bakertilly.bt_hrm_api.app.common.persistence.AuditableSoftDeleteEntity;
import com.bakertilly.bt_hrm_api.app.common.persistence.UuidPrimaryKeyEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "hris_master_job_level", schema = "hris")
public class JobLevel extends AuditableSoftDeleteEntity implements UuidPrimaryKeyEntity {
    @Id
    @Column(name = "job_level_id", length = 60, nullable = false)
    private String jobLevelId;
    @Column(name = "company_id", length = 60, nullable = false)
    private String companyId;
    @Column(name = "job_level_code", length = 50, nullable = false)
    private String jobLevelCode;
    @Column(name = "job_level_name", nullable = false)
    private String jobLevelName;
    @Column(name = "job_level_name_local")
    private String jobLevelNameLocal;
    @Column(name = "job_level_group", length = 100)
    private String jobLevelGroup;
    @Column(name = "level_rank", nullable = false)
    private Integer levelRank;
    @Column(name = "sort_order", nullable = false)
    private Integer sortOrder = 0;
    @Column(name = "description", length = 1000)
    private String description;
    @Override public String getPrimaryKeyValue() { return jobLevelId; }
    @Override public void setPrimaryKeyValue(String primaryKeyValue) { jobLevelId = primaryKeyValue; }
    @Override public String getPrimaryKeyFieldName() { return "jobLevelId"; }
}
