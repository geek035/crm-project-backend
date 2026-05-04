package fqw.crmprojectbackend.company.adapter.out.persistence.repository.contact;

import fqw.crmprojectbackend.company.adapter.out.persistence.entity.contact.CompanyContactRoleJPAEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CompanyContactRoleSpringDataRepository extends
        CrudRepository<CompanyContactRoleJPAEntity, String> {

    Optional<CompanyContactRoleJPAEntity> findByCode(String code);
}
