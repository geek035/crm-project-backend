package fqw.crmprojectbackend.deal.adapter.out.persistence.repository;

import fqw.crmprojectbackend.deal.adapter.out.persistence.entity.DealProductJPAEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DealProductSpringDataRepository
        extends CrudRepository<DealProductJPAEntity, String> {
    Optional<DealProductJPAEntity> findByCode(String code);
}
