package fqw.crmprojectbackend.individual.application.command;

import java.time.LocalDate;
import java.util.UUID;

public record IndividualUpdateCommand(
        UUID id,
        String firstName,
        String secondName,
        String surname,
        String email,
        String phoneNumber,
        LocalDate birthdate
) {}