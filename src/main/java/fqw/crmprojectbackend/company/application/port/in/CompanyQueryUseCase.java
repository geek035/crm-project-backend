package fqw.crmprojectbackend.company.application.port.in;

import fqw.crmprojectbackend.company.application.dto.CompanyDTO;
import fqw.crmprojectbackend.company.application.dto.CompanyPageDTO;
import fqw.crmprojectbackend.company.application.query.CompanyQueryParams;

import java.util.UUID;

public interface CompanyQueryUseCase {
    CompanyDTO findById(UUID id);

    CompanyPageDTO findByParams(CompanyQueryParams params);
}
