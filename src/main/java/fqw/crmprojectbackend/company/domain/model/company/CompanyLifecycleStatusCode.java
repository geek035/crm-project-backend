package fqw.crmprojectbackend.company.domain.model.company;

import fqw.crmprojectbackend.company.domain.exception.CompanyIllegalLifecycleStatusException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CompanyLifecycleStatusCode {
    PROSPECT("Потенциальный"),
    ACTIVE("Активный"),
    INACTIVE("Неактивный"),
    ARCHIVED("Архивированный");

    private final String description;

    public static CompanyLifecycleStatusCode getByCode(String code) {
        return switch (code) {
            case "PROSPECT" -> CompanyLifecycleStatusCode.PROSPECT;
            case "ACTIVE" -> CompanyLifecycleStatusCode.ACTIVE;
            case "INACTIVE" -> CompanyLifecycleStatusCode.INACTIVE;
            case "ARCHIVED" -> CompanyLifecycleStatusCode.ARCHIVED;
            default ->  throw new CompanyIllegalLifecycleStatusException(
                    String.format("Неизвестный жизненный цикл компании '%s'", code));
        };
    }

}
