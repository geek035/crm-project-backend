package fqw.crmprojectbackend.individual.adapter.out.persistence;

import fqw.crmprojectbackend.common.persistent.jpa.exception.RepositoryConstraintException;
import fqw.crmprojectbackend.common.persistent.jpa.spectification.FilterSpecificationBuilder;
import fqw.crmprojectbackend.individual.adapter.out.persistence.entity.IndividualJPAEntity;
import fqw.crmprojectbackend.individual.adapter.out.persistence.mapper.IndividualPersistenceMapper;
import fqw.crmprojectbackend.individual.application.port.out.IndividualRepository;
import fqw.crmprojectbackend.individual.application.query.IndividualByParamsQuery;
import fqw.crmprojectbackend.individual.domain.model.Individual;
import fqw.crmprojectbackend.individual.domain.model.IndividualEmail;
import fqw.crmprojectbackend.individual.domain.model.IndividualID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class IndividualPersistenceJPAAdapter implements IndividualRepository {
    private final IndividualSpringDataRepository individualSpringDataRepository;

    @Override
    @Transactional
    public Individual save(Individual individual) {
        try {
            var entity = IndividualPersistenceMapper.fromDomainModel(individual);
            var saved = this.individualSpringDataRepository.saveAndFlush(entity);

            return IndividualPersistenceMapper.toDomainModel(saved);
        } catch (DataIntegrityViolationException exception) {
            throw new RepositoryConstraintException("Нарушение ограничения уникальности поля email");
        }
    }

    @Override
    public Optional<Individual> findByEmail(IndividualEmail email) {
        return this.individualSpringDataRepository
                .findByEmail(email.value())
                .map(IndividualPersistenceMapper::toDomainModel);
    }

    @Override
    public Optional<Individual> findById(IndividualID id) {
        return this.individualSpringDataRepository
                .findById(id.getValue())
                .map(IndividualPersistenceMapper::toDomainModel);
    }

    @Override
    public List<Individual> findByParams(IndividualByParamsQuery query) {
        Specification<IndividualJPAEntity> specification =
                new FilterSpecificationBuilder<IndividualJPAEntity>()
                        .with(query.filters())
                        .build();

        var orders = query.sort().stream()
                .map(it -> new Sort.Order(
                        Sort.Direction.fromString(it.direction().toString().toLowerCase()),
                        it.field()))
                .toList();

        var sort = Sort.by(orders);
        var pageRequest = PageRequest.of(query.pageNumber(), query.pageSize(), sort);

        var list = this.individualSpringDataRepository.findAll(specification, pageRequest);

        return list.stream()
                .map(IndividualPersistenceMapper::toDomainModel)
                .toList();
    }

    @Override
    public Long getTotal() {
        return this.individualSpringDataRepository.count();
    }
}
