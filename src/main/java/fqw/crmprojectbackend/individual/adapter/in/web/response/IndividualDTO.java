package fqw.crmprojectbackend.individual.adapter.in.web.response;

import java.util.UUID;

public record IndividualDTO(
        UUID id,
        String firstName,
        String secondName,
        String surname,
        String email,
        String phoneNumber) {
}