package fqw.crmprojectbackend.company.application.port.in;

import fqw.crmprojectbackend.common.query.BaseQueryParams;
import fqw.crmprojectbackend.company.application.dto.CompanyContactDTO;
import fqw.crmprojectbackend.company.application.dto.CompanyContactPageDTO;

import java.util.UUID;

public interface CompanyContactQueryUseCase {
    CompanyContactDTO findById(UUID id);

    CompanyContactPageDTO findByParams(UUID companyID, BaseQueryParams params);
}
