package fqw.crmprojectbackend.company.application.port.in;

import fqw.crmprojectbackend.company.application.command.CompanyContactAddCommand;

import java.util.UUID;

public interface CompanyContactAddUseCase {
    UUID addContact(UUID companyID, CompanyContactAddCommand command);
}
