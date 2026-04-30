package fqw.crmprojectbackend.company.application.dto;

import java.util.UUID;

public record IndividualContactDTO(
        UUID id,
        String firstName,
        String lastName,
        String surname,
        String email,
        String phoneNumber) {
}
