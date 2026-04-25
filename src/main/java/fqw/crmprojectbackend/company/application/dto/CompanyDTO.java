package fqw.crmprojectbackend.company.application.dto;

import fqw.crmprojectbackend.common.dto.DirectoryEntryDTO;

import java.util.UUID;

public record CompanyDTO(
        UUID id,
        String officialName,
        String commercialName,
        String companyINN,
        String companyKPP,
        DirectoryEntryDTO companyClientSegment,
        DirectoryEntryDTO lifecycleStatus,
        RegisteredAddressDTO registeredAddress){

}
