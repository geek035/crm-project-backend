package fqw.crmprojectbackend.deal.application.command;

import java.time.LocalDate;

public record DealUpdateCommand(
    String title,
    String description,
    String priorityCode,
    String sourceCode,
    LocalDate expectedCloseDate
) {
}
