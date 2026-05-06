package fqw.crmprojectbackend.company.domain.model.contact;

import fqw.crmprojectbackend.company.domain.exception.contact.CompanyContactIllegalRoleException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CompanyContactRoleCode {
    OWNER("Владелец"),
    CEO("Генеральный директов"),
    FINANCE_DIRECTOR("Финансовый директор"),
    ACCOUNTANT("Бухгалтер"),
    TREASURER("Казначей"),
    OPERATIONS_MANAGER("Операционный менеджер"),
    AUTHORIZED_REPRESENTATIVE("Уполномоченный представитель");

    private final String description;

    public static CompanyContactRoleCode getByCode(String code) {
        return switch (code) {
            case "OWNER" -> CompanyContactRoleCode.OWNER;
            case "CEO" -> CompanyContactRoleCode.CEO;
            case "FINANCE_DIRECTOR" -> CompanyContactRoleCode.FINANCE_DIRECTOR;
            case "ACCOUNTANT" -> CompanyContactRoleCode.ACCOUNTANT;
            case "TREASURER" -> CompanyContactRoleCode.TREASURER;
            case "OPERATIONS_MANAGER" -> CompanyContactRoleCode.OPERATIONS_MANAGER;
            case "AUTHORIZED_REPRESENTATIVE" -> CompanyContactRoleCode.AUTHORIZED_REPRESENTATIVE;
            default -> throw new CompanyContactIllegalRoleException(String.format(
                    "Неизвестная роль контакта '%s'",
                    code));
        };
    }
}
