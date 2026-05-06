package fqw.crmprojectbackend.deal.adapter.out.persistence.repository;

import fqw.crmprojectbackend.deal.adapter.out.persistence.entity.DealPriorityJPAEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DealPrioritySpringDataRepository
        extends CrudRepository<DealPriorityJPAEntity, String> {
    Optional<DealPriorityJPAEntity> findByCode(String code);
}
