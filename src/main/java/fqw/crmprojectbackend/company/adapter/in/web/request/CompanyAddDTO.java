package fqw.crmprojectbackend.company.adapter.in.web.request;

import fqw.crmprojectbackend.company.application.dto.RegisteredAddressDTO;
import jakarta.validation.constraints.NotNull;

public record CompanyAddDTO(
        @NotNull(message = "Официальное название компании должно быть инициализировано")
        String officialName,
        @NotNull(message = "Коммерческое название компании должно быть инициализировано")
        String commercialName,
        @NotNull(message = "ИНН компании должнен быть указан")
        String inn,
        @NotNull(message = "КПП компании должнен быть инициализирован")
        String kpp,
        @NotNull(message = "Сегмент дейтельности компании должен быть указан")
        String clientSegmentCode,
        @NotNull(message = "Адрес регистрации должен быть указан")
        RegisteredAddressDTO registeredAddress
) {
}
