package fqw.crmprojectbackend.individual.application.service;

import fqw.crmprojectbackend.common.persistent.jpa.exception.RepositoryConstraintException;
import fqw.crmprojectbackend.individual.domain.exception.IndividualDuplicateEmailException;
import fqw.crmprojectbackend.individual.application.command.IndividualAddCommand;
import fqw.crmprojectbackend.individual.application.port.in.IndividualAddUseCase;
import fqw.crmprojectbackend.individual.application.port.out.IndividualRepository;
import fqw.crmprojectbackend.individual.domain.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class IndividualAddService implements IndividualAddUseCase {
    private final IndividualRepository individualRepository;

    @Override
    public UUID addIndividual(IndividualAddCommand command) throws IndividualDuplicateEmailException {
        try {
            var individualToSave = new Individual(
                    IndividualID.generateID(),
                    new IndividualFullName(command.firstName(), command.secondName(), command.surname()),
                    new IndividualEmail(command.email()),
                    new IndividualPhoneNumber(command.phoneNumber()),
                    new IndividualBirthdate(command.birthdate())
            );

            var individual = this.individualRepository.save(individualToSave);

            return individual.id().getValue();

        } catch (RepositoryConstraintException exception) {
            throw new IndividualDuplicateEmailException(
                    String.format(
                            "Физ. лицо с email '%s' уже существует",
                            command.email()));
        }

    }
}
