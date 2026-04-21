package fqw.crmprojectbackend.individual.application.service;

import fqw.crmprojectbackend.common.persistent.jpa.exception.RepositoryConstraintException;
import fqw.crmprojectbackend.individual.application.mapper.IndividualApplicationMapper;
import fqw.crmprojectbackend.individual.domain.exception.IndividualDuplicateEmailException;
import fqw.crmprojectbackend.individual.application.command.IndividualAddCommand;
import fqw.crmprojectbackend.individual.application.port.in.IndividualAddUseCase;
import fqw.crmprojectbackend.individual.application.port.out.IndividualRepositoryPort;
import fqw.crmprojectbackend.individual.domain.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class IndividualAddService implements IndividualAddUseCase {
    private final IndividualRepositoryPort individualRepositoryPort;

    @Override
    public UUID addIndividual(IndividualAddCommand command) throws IndividualDuplicateEmailException {
        var email = new IndividualEmail(command.email());

        if (this.individualRepositoryPort.existByEmail(email)) {
            throw new IndividualDuplicateEmailException(
                    String.format(
                            "Физ. лицо с email '%s' уже существует",
                            command.email()));
        }

        var individualToSave = IndividualApplicationMapper.toIndividualModel(command);
        var individual = this.individualRepositoryPort.save(individualToSave);

        return individual.getId().getValue();
    }
}
