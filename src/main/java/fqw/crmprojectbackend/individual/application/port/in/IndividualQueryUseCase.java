package fqw.crmprojectbackend.individual.application.port.in;

import fqw.crmprojectbackend.individual.application.dto.IndividualDTO;
import fqw.crmprojectbackend.individual.application.dto.IndividualPageDTO;
import fqw.crmprojectbackend.individual.application.query.IndividualByIDQuery;
import fqw.crmprojectbackend.individual.application.query.IndividualByParamsQuery;

import java.util.Optional;

public interface IndividualQueryUseCase {
    Optional<IndividualDTO> findById(IndividualByIDQuery command);

    IndividualPageDTO findByParams(IndividualByParamsQuery command);
}
