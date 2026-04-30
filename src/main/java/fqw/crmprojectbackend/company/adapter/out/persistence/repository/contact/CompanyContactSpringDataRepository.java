package fqw.crmprojectbackend.company.adapter.out.persistence.repository.contact;

import fqw.crmprojectbackend.company.adapter.out.persistence.entity.contact.CompanyContactJPAEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CompanyContactSpringDataRepository
        extends JpaRepository<CompanyContactJPAEntity, UUID> {

    boolean existsByIndividualID(UUID individualID);
}
