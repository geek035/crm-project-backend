package fqw.crmprojectbackend.company.application.port.out;

import fqw.crmprojectbackend.company.application.dto.CompanyDTO;
import fqw.crmprojectbackend.company.application.query.CompanyQueryParams;
import fqw.crmprojectbackend.company.domain.model.company.Company;
import fqw.crmprojectbackend.company.domain.model.company.CompanyID;
import fqw.crmprojectbackend.company.domain.model.company.CompanyINN;

import java.util.List;
import java.util.Optional;

public interface CompanyRepositoryPort {
    boolean existsByINN(CompanyINN inn);

    CompanyID add(Company company);

    Optional<Company> findByID(CompanyID id);

    List<Company> findByParams(CompanyQueryParams params);

    long getTotal();
}
