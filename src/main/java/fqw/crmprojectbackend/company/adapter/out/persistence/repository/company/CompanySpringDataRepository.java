package fqw.crmprojectbackend.company.adapter.out.persistence.repository.company;

import fqw.crmprojectbackend.company.adapter.out.persistence.entity.company.CompanyJPAEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;
import java.util.UUID;

public interface CompanySpringDataRepository extends
        JpaRepository<CompanyJPAEntity, UUID>,
        JpaSpecificationExecutor<CompanyJPAEntity> {

    boolean existsByInn(String inn);

    @Override
    @EntityGraph(attributePaths = {
            "clientSegment",
            "lifecycleStatus",
            "registeredAddress"
    })
    Optional<CompanyJPAEntity> findById(UUID id);

    @EntityGraph(attributePaths = {
            "clientSegment",
            "lifecycleStatus",
            "registeredAddress"
    })
    Optional<CompanyJPAEntity> findByInn(String inn);

    @Override
    @EntityGraph(attributePaths = {
            "clientSegment",
            "lifecycleStatus",
            "registeredAddress"
    })
    Page<CompanyJPAEntity> findAll(
            Specification<CompanyJPAEntity> specification,
            Pageable pageable);
}
