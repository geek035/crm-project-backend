package fqw.crmprojectbackend.deal.application.command;

public record DealChangeStatusCommand(
        String statusCode,
        String closeInfo
) {
}
