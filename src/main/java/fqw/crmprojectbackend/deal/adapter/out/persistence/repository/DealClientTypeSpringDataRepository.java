package fqw.crmprojectbackend.deal.adapter.out.persistence.repository;

import fqw.crmprojectbackend.deal.adapter.out.persistence.entity.DealClientTypeJPAEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DealClientTypeSpringDataRepository
        extends CrudRepository<DealClientTypeJPAEntity, String> {
    Optional<DealClientTypeJPAEntity> findByCode(String code);
}
