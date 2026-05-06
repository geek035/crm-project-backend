package fqw.crmprojectbackend.deal.application.command;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record DealCreateCommand(
        String number,
        String clientTypeCode,
        UUID clientID,
        String title,
        String description,
        String productCode,
        BigDecimal amount,
        String currencyCode,
        String priorityCode,
        String sourceCode,
        LocalDate expectedCloseDate) {
}
