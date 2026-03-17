package fqw.crmprojectbackend.individual.application.port.out;

import fqw.crmprojectbackend.individual.domain.model.Individual;
import fqw.crmprojectbackend.individual.domain.model.IndividualEmail;
import fqw.crmprojectbackend.individual.domain.model.IndividualID;

import java.util.Optional;

public interface IndividualRepository {
    Individual save(Individual individual);

    Optional<Individual> findByEmail(IndividualEmail email);

    Optional<Individual> findById(IndividualID id);
}
