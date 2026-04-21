package fqw.crmprojectbackend.individual.application.port.out;

import fqw.crmprojectbackend.individual.application.query.IndividualByParamsQuery;
import fqw.crmprojectbackend.individual.domain.exception.IndividualDuplicateEmailException;
import fqw.crmprojectbackend.individual.domain.model.Individual;
import fqw.crmprojectbackend.individual.domain.model.IndividualEmail;
import fqw.crmprojectbackend.individual.domain.model.IndividualID;

import java.util.List;
import java.util.Optional;

public interface IndividualRepositoryPort {
    Individual save(Individual individual);

    Optional<Individual> findByEmail(IndividualEmail email);

    boolean existByEmail(IndividualEmail email);

    Optional<Individual> findById(IndividualID id);

    List<Individual> findByParams(IndividualByParamsQuery query);

    Long getTotal();

    Individual update(Individual individual) throws IndividualDuplicateEmailException;
}
