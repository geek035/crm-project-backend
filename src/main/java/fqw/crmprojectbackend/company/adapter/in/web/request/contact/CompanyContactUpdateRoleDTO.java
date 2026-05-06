package fqw.crmprojectbackend.company.adapter.in.web.request.contact;

import jakarta.validation.constraints.NotNull;

public record CompanyContactUpdateRoleDTO(
        @NotNull(message = "Код роли обязателен")
        String roleCode
) { }
