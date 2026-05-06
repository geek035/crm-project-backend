package fqw.crmprojectbackend.company.adapter.out.persistence.repository.contact;

import fqw.crmprojectbackend.company.adapter.out.persistence.entity.contact.CompanyContactJPAEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;
import java.util.UUID;

public interface CompanyContactSpringDataRepository extends
        JpaRepository<CompanyContactJPAEntity, UUID>,
        JpaSpecificationExecutor<CompanyContactJPAEntity> {

    boolean existsById(UUID id);
    boolean existsByIndividualID(UUID individualID);

    @Override
    @EntityGraph(attributePaths = {
            "role",
            "status"
    })
    Optional<CompanyContactJPAEntity> findById(UUID id);

    @Override
    @EntityGraph(attributePaths = {
            "role",
            "status"
    })
    Page<CompanyContactJPAEntity> findAll(
            Specification<CompanyContactJPAEntity> specification,
            Pageable pageable);
}
