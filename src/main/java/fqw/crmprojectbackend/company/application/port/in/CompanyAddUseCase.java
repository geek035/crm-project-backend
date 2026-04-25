package fqw.crmprojectbackend.company.application.port.in;

import fqw.crmprojectbackend.company.application.command.CompanyAddCommand;
import fqw.crmprojectbackend.company.application.dto.CompanyDTO;
import fqw.crmprojectbackend.company.domain.exception.CompanyDuplicateINNException;
import fqw.crmprojectbackend.company.domain.model.company.CompanyID;

public interface CompanyAddUseCase {
    CompanyID add(CompanyAddCommand command) throws CompanyDuplicateINNException;
}
