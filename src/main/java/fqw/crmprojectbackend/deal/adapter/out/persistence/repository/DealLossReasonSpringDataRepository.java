package fqw.crmprojectbackend.deal.adapter.out.persistence.repository;

import fqw.crmprojectbackend.deal.adapter.out.persistence.entity.DealLossReasonJPAEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DealLossReasonSpringDataRepository
        extends CrudRepository<DealLossReasonJPAEntity, String> {
    Optional<DealLossReasonJPAEntity> findByCode(String code);
}
