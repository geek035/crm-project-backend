package fqw.crmprojectbackend.individual.application.port.out;

import fqw.crmprojectbackend.individual.domain.model.Individual;
import fqw.crmprojectbackend.individual.domain.model.IndividualEmail;

import java.util.Optional;
import java.util.UUID;

public interface IndividualRepository {
    Individual save(Individual individual);

    Optional<Individual> findByEmail(IndividualEmail email);
}
