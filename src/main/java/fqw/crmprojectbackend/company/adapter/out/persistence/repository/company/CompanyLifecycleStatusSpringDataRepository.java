package fqw.crmprojectbackend.company.adapter.out.persistence.repository.company;

import fqw.crmprojectbackend.company.adapter.out.persistence.entity.company.CompanyLifecycleStatusJPAEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CompanyLifecycleStatusSpringDataRepository
        extends CrudRepository<CompanyLifecycleStatusJPAEntity, String> {

    Optional<CompanyLifecycleStatusJPAEntity> findByCode(String code);
}
