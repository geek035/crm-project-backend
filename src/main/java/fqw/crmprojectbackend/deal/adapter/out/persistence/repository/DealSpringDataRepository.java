package fqw.crmprojectbackend.deal.adapter.out.persistence.repository;

import fqw.crmprojectbackend.deal.adapter.out.persistence.entity.DealJPAEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;
import java.util.UUID;

public interface DealSpringDataRepository extends
        JpaRepository<DealJPAEntity, UUID>,
        JpaSpecificationExecutor<DealJPAEntity> {
    boolean existsByNumber(String number);

    @Override
    @EntityGraph(attributePaths = {
            "clientType",
            "product",
            "currency",
            "stage",
            "status",
            "priority",
            "source",
            "lossReason"
    })
    Optional<DealJPAEntity> findById(UUID id);

    @Override
    @EntityGraph(attributePaths = {
            "clientType",
            "product",
            "currency",
            "stage",
            "status",
            "priority",
            "source",
            "lossReason"
    })
    Page<DealJPAEntity> findAll(
            Specification<DealJPAEntity> specification,
            Pageable pageable);
}
