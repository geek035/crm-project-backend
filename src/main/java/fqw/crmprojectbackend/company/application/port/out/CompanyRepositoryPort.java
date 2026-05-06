package fqw.crmprojectbackend.company.application.port.out;

import fqw.crmprojectbackend.company.application.dto.CompanyDTO;
import fqw.crmprojectbackend.company.application.query.CompanyQueryParams;
import fqw.crmprojectbackend.company.application.request.CompanyUpdateRequest;
import fqw.crmprojectbackend.company.domain.model.company.Company;
import fqw.crmprojectbackend.company.domain.model.company.CompanyID;
import fqw.crmprojectbackend.company.domain.model.company.CompanyINN;

import java.util.List;
import java.util.Optional;

public interface CompanyRepositoryPort {
    boolean existByINN(CompanyINN inn);
    boolean existByID(CompanyID id);

    CompanyID add(Company company);
    CompanyDTO updateByOrigin(CompanyDTO origin);

    long getTotal();
    Optional<Company> findByID(CompanyID id);
    List<Company> findByParams(CompanyQueryParams params);
    Optional<Company> findByINN(CompanyINN inn);
}
