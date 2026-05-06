package fqw.crmprojectbackend.deal.adapter.out.persistence.repository;

import fqw.crmprojectbackend.deal.adapter.out.persistence.entity.DealStageJPAEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DealStageSpringDataRepository
        extends CrudRepository<DealStageJPAEntity, String> {
    Optional<DealStageJPAEntity> findByCode(String code);
}
