package fqw.crmprojectbackend.company.domain.model.company;

import fqw.crmprojectbackend.company.domain.exception.CompanyIllegalClientSegmentException;
import fqw.crmprojectbackend.company.domain.exception.CompanyIllegalLifecycleStatusException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CompanyLifecycleStatusType {
    PROSPECT("Потенциальный"),
    ACTIVE("Активный"),
    INACTIVE("Неактивный"),
    ARCHIVED("Архивированный");

    private final String description;

    public static CompanyLifecycleStatusType getByCode(String code) {
        return switch (code) {
            case "PROSPECT" -> CompanyLifecycleStatusType.PROSPECT;
            case "ACTIVE" -> CompanyLifecycleStatusType.ACTIVE;
            case "INACTIVE" -> CompanyLifecycleStatusType.INACTIVE;
            case "ARCHIVED" -> CompanyLifecycleStatusType.ARCHIVED;
            default ->  throw new CompanyIllegalLifecycleStatusException(
                    String.format("Неизвестный жизненный цикл компании '%s'", code));
        };
    }

}
