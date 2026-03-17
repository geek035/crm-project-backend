package fqw.crmprojectbackend.individual.application.response;

import java.time.LocalDate;
import java.util.UUID;

public record IndividualResponse(
        UUID id,
        String firstName,
        String secondName,
        String surname,
        String email,
        String phoneNumber,
        LocalDate birthdate
) {}
