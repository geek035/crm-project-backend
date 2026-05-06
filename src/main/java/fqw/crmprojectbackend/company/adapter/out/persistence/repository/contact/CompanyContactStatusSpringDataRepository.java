package fqw.crmprojectbackend.company.adapter.out.persistence.repository.contact;

import fqw.crmprojectbackend.company.adapter.out.persistence.entity.contact.CompanyContactStatusJPAEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CompanyContactStatusSpringDataRepository extends
        CrudRepository<CompanyContactStatusJPAEntity, String> {

    Optional<CompanyContactStatusJPAEntity> findByCode(String code);
}
