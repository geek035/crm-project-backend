package fqw.crmprojectbackend.individual.application.port.in;

import fqw.crmprojectbackend.individual.application.command.IndividualUpdateCommand;
import fqw.crmprojectbackend.individual.application.dto.IndividualDTO;
import fqw.crmprojectbackend.individual.domain.exception.IndividualDuplicateEmailException;

public interface IndividualUpdateUseCase {
    IndividualDTO update(IndividualUpdateCommand command) throws IndividualDuplicateEmailException;
}
