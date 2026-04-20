package fqw.crmprojectbackend.individual.application.port.in;

import fqw.crmprojectbackend.individual.application.command.IndividualUpdateCommand;
import fqw.crmprojectbackend.individual.application.response.IndividualResponse;

public interface IndividualUpdateUseCase {
    IndividualResponse update(IndividualUpdateCommand command);
}
