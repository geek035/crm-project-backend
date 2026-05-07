package fqw.crmprojectbackend.deal.application.port.out;

import fqw.crmprojectbackend.deal.application.dto.DealDTO;
import fqw.crmprojectbackend.deal.application.query.DealQueryParams;
import fqw.crmprojectbackend.deal.application.request.DealAddRequest;
import fqw.crmprojectbackend.deal.domain.model.deal.DealNumber;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DealRepositoryPort {
    boolean existsByNumber(DealNumber number);

    UUID add(DealAddRequest origin);
    DealDTO updateByOrigin(DealDTO origin);

    long getTotal();
    Optional<DealDTO> findByID(UUID id);
    List<DealDTO> findByParams(DealQueryParams params);
    List<DealDTO> findByCompanyID(UUID companyID, DealQueryParams params);
    List<DealDTO> findByIndividualID(UUID individualID, DealQueryParams params);


}
