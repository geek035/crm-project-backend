package fqw.crmprojectbackend.company.application.command;

import fqw.crmprojectbackend.company.application.dto.RegisteredAddressDTO;

public record CompanyAddCommand(
        String officialName,
        String commercialName,
        String inn,
        String kpp,
        String clientSegmentCode,
        RegisteredAddressDTO registeredAddress) {
}
