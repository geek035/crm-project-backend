package fqw.crmprojectbackend.company.domain.model.contact;

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
}
