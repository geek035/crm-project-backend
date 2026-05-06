package fqw.crmprojectbackend.deal.adapter.out.persistence.repository;

import fqw.crmprojectbackend.deal.adapter.out.persistence.entity.DealStatusJPAEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DealStatusSpringDataRepository
        extends CrudRepository<DealStatusJPAEntity, String> {
    Optional<DealStatusJPAEntity> findByCode(String code);
}
