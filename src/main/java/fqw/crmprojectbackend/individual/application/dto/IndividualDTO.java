package fqw.crmprojectbackend.individual.application.dto;

import java.time.LocalDate;
import java.util.UUID;

public record IndividualDTO(
        UUID id,
        String firstName,
        String secondName,
        String surname,
        String email,
        String phoneNumber,
        LocalDate birthdate) {
}