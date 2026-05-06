package fqw.crmprojectbackend.company.adapter.in.web.request.contact;

import jakarta.validation.constraints.NotNull;

public record CompanyContactUpdateStatusDTO(
        @NotNull(message = "Код статуса контакта обязателен")
        String statusCode
) {
}
