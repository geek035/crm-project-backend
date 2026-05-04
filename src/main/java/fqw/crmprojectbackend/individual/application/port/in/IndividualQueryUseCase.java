package fqw.crmprojectbackend.individual.application.port.in;

import fqw.crmprojectbackend.individual.application.dto.IndividualDTO;
import fqw.crmprojectbackend.individual.application.dto.IndividualPageDTO;
import fqw.crmprojectbackend.individual.application.query.IndividualByIDQuery;
import fqw.crmprojectbackend.individual.application.query.IndividualByParamsQuery;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IndividualQueryUseCase {
    Optional<IndividualDTO> findById(IndividualByIDQuery command);
    List<IndividualDTO> findByIDs(Collection<UUID> ids);
    IndividualPageDTO findByParams(IndividualByParamsQuery command);
}
