package fqw.crmprojectbackend.company.adapter.in.web.request.contact;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CompanyContactAddDTO(
        @NotNull(message = "Необходим идентификатор физ. лица")
        UUID individualID,
        @NotNull(message = "Необходима роль контакта")
        String roleCode
) {
}
