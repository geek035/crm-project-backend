package fqw.crmprojectbackend.deal.adapter.out.persistence.repository;

import fqw.crmprojectbackend.deal.adapter.out.persistence.entity.DealJPAEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface DealSpringDataRepository extends JpaRepository<DealJPAEntity, UUID> {
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
}
