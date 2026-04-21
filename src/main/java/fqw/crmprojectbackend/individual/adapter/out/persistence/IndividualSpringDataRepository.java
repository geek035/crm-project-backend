package fqw.crmprojectbackend.individual.adapter.out.persistence;


import fqw.crmprojectbackend.individual.adapter.out.persistence.entity.IndividualJPAEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;
import java.util.UUID;

public interface IndividualSpringDataRepository extends
        JpaRepository<IndividualJPAEntity, UUID>,
        JpaSpecificationExecutor<IndividualJPAEntity> {

    Optional<IndividualJPAEntity> findByEmail(String email);
    boolean existsByEmail(String email);
}