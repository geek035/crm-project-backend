package fqw.crmprojectbackend.company.domain.model.contact;

import fqw.crmprojectbackend.company.domain.exception.contact.CompanyContactIllegalStatusException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CompanyContactStatusCode {
    ACTIVE("Активный"),
    INACTIVE("Неактивный"),
    DISMISSED("Уволен"),
    TEMPORARY_UNAVAILABLE("Временно не доступен");

    private final String description;

    public static CompanyContactStatusCode getByCode(String code) {
        return switch (code) {
            case "ACTIVE" -> CompanyContactStatusCode.ACTIVE;
            case "INACTIVE" -> CompanyContactStatusCode.INACTIVE;
            case "DISMISSED" -> CompanyContactStatusCode.DISMISSED;
            case "TEMPORARY_UNAVAILABLE" -> CompanyContactStatusCode.TEMPORARY_UNAVAILABLE;
            default -> throw new CompanyContactIllegalStatusException(String.format(
                    "Неизвестный статус контакта '%s'", code));
        };
    }
}
