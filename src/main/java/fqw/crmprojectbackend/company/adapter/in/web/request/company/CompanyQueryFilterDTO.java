package fqw.crmprojectbackend.company.adapter.in.web.request.company;

import jakarta.validation.constraints.NotNull;

public record CompanyQueryFilterDTO(
        @NotNull
        String field,
        @NotNull
        Object value,
        @NotNull
        String matchMode) {}
