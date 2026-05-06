package fqw.crmprojectbackend.deal.adapter.out.persistence.repository;

import fqw.crmprojectbackend.deal.adapter.out.persistence.entity.DealSourceJPAEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DealSourceSpringDataRepository
        extends CrudRepository<DealSourceJPAEntity, String> {
    Optional<DealSourceJPAEntity> findByCode(String code);
}
