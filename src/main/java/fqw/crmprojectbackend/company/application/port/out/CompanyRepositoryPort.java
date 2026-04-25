package fqw.crmprojectbackend.company.application.port.out;

import fqw.crmprojectbackend.company.domain.model.company.Company;
import fqw.crmprojectbackend.company.domain.model.company.CompanyID;
import fqw.crmprojectbackend.company.domain.model.company.CompanyINN;

public interface CompanyRepositoryPort {
    public boolean existsByINN(CompanyINN inn);

    public CompanyID add(Company company);
}
