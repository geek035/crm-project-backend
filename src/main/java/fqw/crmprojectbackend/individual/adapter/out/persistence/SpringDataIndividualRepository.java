package fqw.crmprojectbackend.individual.adapter.out.persistence;


import fqw.crmprojectbackend.individual.adapter.out.persistence.entity.IndividualJPAEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SpringDataIndividualRepository extends JpaRepository<IndividualJPAEntity, UUID> {
    Optional<IndividualJPAEntity> findByEmail(String email);
}