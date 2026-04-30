package fqw.crmprojectbackend.company.application.dto;

import fqw.crmprojectbackend.common.dto.DirectoryEntryDTO;

import java.util.UUID;

public record CompanyContactDTO(
        UUID id,
        IndividualContactDTO individualContact,
        DirectoryEntryDTO role,
        DirectoryEntryDTO status) {
}
