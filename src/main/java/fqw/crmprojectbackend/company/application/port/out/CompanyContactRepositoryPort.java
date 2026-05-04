package fqw.crmprojectbackend.company.application.port.out;

import fqw.crmprojectbackend.common.query.BaseQueryParams;
import fqw.crmprojectbackend.company.application.dto.CompanyContactDTO;
import fqw.crmprojectbackend.company.application.request.CompanyContactAddRequest;
import fqw.crmprojectbackend.company.application.request.CompanyContactUpdateRequest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CompanyContactRepositoryPort {
    UUID addContact(CompanyContactAddRequest request);

    CompanyContactDTO updateByOrigin(CompanyContactDTO origin);

    boolean existByIndividualID(UUID individualID);

    long getTotal();
    Optional<CompanyContactDTO> findByID(UUID id);
    List<CompanyContactDTO> findByParams(UUID companyID, BaseQueryParams params);
}
