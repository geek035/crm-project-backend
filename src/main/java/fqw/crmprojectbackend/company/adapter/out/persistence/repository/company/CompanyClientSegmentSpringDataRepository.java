package fqw.crmprojectbackend.company.adapter.out.persistence.repository.company;

import fqw.crmprojectbackend.company.adapter.out.persistence.entity.company.CompanyClientSegmentJPAEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CompanyClientSegmentSpringDataRepository
        extends CrudRepository<CompanyClientSegmentJPAEntity, String> {

    Optional<CompanyClientSegmentJPAEntity> findByCode(String code);
}
