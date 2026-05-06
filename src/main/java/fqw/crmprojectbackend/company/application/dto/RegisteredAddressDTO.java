package fqw.crmprojectbackend.company.application.dto;

import jakarta.validation.constraints.NotNull;

public record RegisteredAddressDTO(
        @NotNull()
        String country,

        @NotNull()
        String region,

        @NotNull()
        String city,

        @NotNull()
        String street,

        @NotNull()
        String building,

        String office,

        @NotNull()
        String postalCode
) {}
