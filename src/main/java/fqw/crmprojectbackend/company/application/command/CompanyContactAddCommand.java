package fqw.crmprojectbackend.company.application.command;

import java.util.UUID;

public record CompanyContactAddCommand(
        UUID individualID,
        String roleCode) {}
