package fqw.crmprojectbackend.individual.adapter.out.persistence;

import fqw.crmprojectbackend.individual.adapter.out.persistence.mapper.IndividualPersistenceMapper;
import fqw.crmprojectbackend.individual.application.port.out.IndividualRepository;
import fqw.crmprojectbackend.individual.domain.model.Individual;
import fqw.crmprojectbackend.individual.domain.model.IndividualEmail;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class JPAIndividualPersistenceAdapter implements IndividualRepository {
    private final SpringDataIndividualRepository springDataIndividualRepository;

    @Override
    public Individual save(Individual individual) {
        var entity = IndividualPersistenceMapper.fromDomainModel(individual);
        var saved = this.springDataIndividualRepository.save(entity);

        return IndividualPersistenceMapper.toDomainModel(saved);
    }

    @Override
    public Optional<Individual> findByEmail(IndividualEmail email) {
        return this.springDataIndividualRepository
                .findByEmail(email.value())
                .map(IndividualPersistenceMapper::toDomainModel);
    }
}
