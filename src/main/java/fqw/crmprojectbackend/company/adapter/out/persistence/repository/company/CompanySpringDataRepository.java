package fqw.crmprojectbackend.company.adapter.out.persistence.repository.company;

import fqw.crmprojectbackend.company.adapter.out.persistence.entity.company.CompanyJPAEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface CompanySpringDataRepository extends
        JpaRepository<CompanyJPAEntity, UUID>,
        JpaSpecificationExecutor<CompanyJPAEntity> {

    boolean existsByInn(String inn);
}
