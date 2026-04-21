package fqw.crmprojectbackend.individual.application.service;

import fqw.crmprojectbackend.individual.application.command.IndividualUpdateCommand;
import fqw.crmprojectbackend.individual.application.dto.IndividualDTO;
import fqw.crmprojectbackend.individual.application.mapper.IndividualApplicationMapper;
import fqw.crmprojectbackend.individual.application.port.in.IndividualUpdateUseCase;
import fqw.crmprojectbackend.individual.application.port.out.IndividualRepositoryPort;
import fqw.crmprojectbackend.individual.domain.exception.IndividualDuplicateEmailException;
import fqw.crmprojectbackend.individual.domain.model.IndividualEmail;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IndividualUpdateService implements IndividualUpdateUseCase {
    private final IndividualRepositoryPort individualRepositoryPort;

    @Override
    public IndividualDTO update(@NonNull IndividualUpdateCommand command) {
        var email = new IndividualEmail(command.email());
        var individualByEmail = this.individualRepositoryPort.findByEmail(email);

        if (individualByEmail.isPresent() && !individualByEmail.get().getId().getValue().equals(command.id())) {
            throw new IndividualDuplicateEmailException( String.format(
                    "Физ. лицо с email '%s' уже существует",
                    command.email()));
        }

        final var updatedIndividual = IndividualApplicationMapper.toIndividualModel(command);
        final var individual = this.individualRepositoryPort.update(updatedIndividual);

        return IndividualApplicationMapper.toIndividualDTO(individual);
    }
}
