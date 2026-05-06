package fqw.crmprojectbackend.deal.application.dto;

import fqw.crmprojectbackend.common.dto.DirectoryEntryDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record DealDTO(
        UUID id,
        String number,
        DirectoryEntryDTO clientType,
        UUID individualID,
        UUID companyID,
        String title,
        String description,
        DirectoryEntryDTO product,
        BigDecimal amount,
        DirectoryEntryDTO currency,
        DirectoryEntryDTO stage,
        DirectoryEntryDTO status,
        Integer probability,
        DirectoryEntryDTO priority,
        DirectoryEntryDTO source,
        LocalDate expectedCloseDate,
        LocalDate actualCloseDate,
        DirectoryEntryDTO lossReason) {
}
