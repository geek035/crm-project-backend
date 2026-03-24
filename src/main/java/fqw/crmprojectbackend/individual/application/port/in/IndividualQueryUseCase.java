package fqw.crmprojectbackend.individual.application.port.in;

import fqw.crmprojectbackend.individual.application.query.IndividualByIDQuery;
import fqw.crmprojectbackend.individual.application.query.IndividualByParamsQuery;
import fqw.crmprojectbackend.individual.application.response.IndividualQueryResponse;
import fqw.crmprojectbackend.individual.application.response.IndividualResponse;

import java.util.List;
import java.util.Optional;

public interface IndividualQueryUseCase {
    Optional<IndividualResponse> findById(IndividualByIDQuery command);

    IndividualQueryResponse findByParams(IndividualByParamsQuery command);
}
