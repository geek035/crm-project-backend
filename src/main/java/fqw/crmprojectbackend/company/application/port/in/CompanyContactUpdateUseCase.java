package fqw.crmprojectbackend.company.application.port.in;

import fqw.crmprojectbackend.company.application.command.CompanyContactUpdateRoleCommand;
import fqw.crmprojectbackend.company.application.command.CompanyContactUpdateStatusCommand;
import fqw.crmprojectbackend.company.application.dto.CompanyContactDTO;

import java.util.UUID;

public interface CompanyContactUpdateUseCase {

    CompanyContactDTO updateRole(UUID id, CompanyContactUpdateRoleCommand command);
    CompanyContactDTO updateStatus(UUID id, CompanyContactUpdateStatusCommand command);
}
