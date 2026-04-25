package fqw.crmprojectbackend.company.domain.model.contact;

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
}
