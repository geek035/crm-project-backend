package fqw.crmprojectbackend.company.application.port.in;

import fqw.crmprojectbackend.company.application.command.CompanyUpdateCommand;
import fqw.crmprojectbackend.company.application.dto.CompanyDTO;
import fqw.crmprojectbackend.company.application.command.CompanyUpdateLifecycleCommand;

import java.util.UUID;

public interface CompanyUpdateUseCase {
    CompanyDTO update(UUID id, CompanyUpdateCommand command);

    CompanyDTO updateLifecycle(UUID id, CompanyUpdateLifecycleCommand command);

}
