package fqw.crmprojectbackend.company.application.command;

public record IndividualContactAddCommand(
        String firstName,
        String secondName,
        String surname,
        String email,
        String phoneNumber
) {}
