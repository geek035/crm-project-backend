package fqw.crmprojectbackend.deal.adapter.out.persistence.repository;

import fqw.crmprojectbackend.deal.adapter.out.persistence.entity.DealCurrencyJPAEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DealCurrencySpringDataRepository
        extends CrudRepository<DealCurrencyJPAEntity, String> {
    Optional<DealCurrencyJPAEntity> findByCode(String code);
}
