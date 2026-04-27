package fqw.crmprojectbackend.company.application.port.in;

import fqw.crmprojectbackend.company.adapter.in.web.request.CompanyUpdateDTO;
import fqw.crmprojectbackend.company.application.command.CompanyUpdateCommand;
import fqw.crmprojectbackend.company.application.dto.CompanyDTO;

import java.util.UUID;

public interface CompanyUpdateUseCase {
    CompanyDTO update(UUID id, CompanyUpdateCommand command);
}
