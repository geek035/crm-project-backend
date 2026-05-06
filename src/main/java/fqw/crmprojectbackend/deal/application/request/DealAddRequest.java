package fqw.crmprojectbackend.deal.application.request;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record DealAddRequest(
        UUID id,
        String number,
        String clientTypeCode,
        UUID individualID,
        UUID companyID,
        String title,
        String description,
        String productCode,
        BigDecimal amount,
        String currencyCode,
        String stageCode,
        String statusCode,
        Integer probability,
        String priorityCode,
        String sourceCode,
        LocalDate expectedCloseDate,
        LocalDate actualCloseDate,
        String lossReasonCode) {
}
