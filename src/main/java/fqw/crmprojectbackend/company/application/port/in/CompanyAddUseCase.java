package fqw.crmprojectbackend.company.application.port.in;

import fqw.crmprojectbackend.company.application.command.CompanyAddCommand;
import fqw.crmprojectbackend.company.domain.exception.company.CompanyDuplicateINNException;
import fqw.crmprojectbackend.company.domain.model.company.CompanyID;

public interface CompanyAddUseCase {
    CompanyID add(CompanyAddCommand command) throws CompanyDuplicateINNException;
}
