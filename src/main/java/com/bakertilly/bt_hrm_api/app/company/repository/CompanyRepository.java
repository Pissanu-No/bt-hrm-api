package com.bakertilly.bt_hrm_api.app.company.repository;

import com.bakertilly.bt_hrm_api.app.company.entity.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, String> {
    Page<Company> findByIsActiveTrueAndDeletedAtIsNull(Pageable pageable);

    Optional<Company> findByCompanyIdAndDeletedAtIsNull(String companyId);

    boolean existsByCompanyCodeAndDeletedAtIsNull(String companyCode);

    boolean existsByCompanyCodeAndCompanyIdNotAndDeletedAtIsNull(String companyCode, String companyId);
}
