package fqw.crmprojectbackend.individual.application.command;

import java.time.LocalDate;

public record IndividualAddCommand(
        String firstName,
        String secondName,
        String surname,
        String email,
        String phoneNumber,
        LocalDate birthdate
) {}
