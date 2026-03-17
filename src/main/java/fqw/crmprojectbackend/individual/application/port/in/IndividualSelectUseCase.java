package fqw.crmprojectbackend.individual.application.port.in;

import fqw.crmprojectbackend.individual.application.command.IndividualSelectByIDCommand;
import fqw.crmprojectbackend.individual.application.command.IndividualSelectByParamsCommand;
import fqw.crmprojectbackend.individual.application.response.IndividualResponse;

import java.util.List;
import java.util.Optional;

public interface IndividualSelectUseCase {
    Optional<IndividualResponse> findById(IndividualSelectByIDCommand command);

    List<IndividualResponse> findByParams(IndividualSelectByParamsCommand command);
}
