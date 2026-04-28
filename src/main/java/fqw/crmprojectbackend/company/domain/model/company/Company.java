package fqw.crmprojectbackend.company.domain.model.company;

import fqw.crmprojectbackend.company.domain.exception.CompanyIllegalLifecycleChangeException;
import fqw.crmprojectbackend.company.domain.exception.CompanyIllegalLifecycleStatusException;
import fqw.crmprojectbackend.company.domain.model.adress.RegisteredAddress;
import lombok.Data;

import java.util.function.Consumer;

@Data
public class Company {
    private CompanyID id;
    private CompanyOfficialName officialName;
    private CompanyCommercialName commercialName;
    private CompanyINN inn;
    private CompanyKPP kpp;
    private CompanyClientSegment clientSegment;
    private CompanyLifecycleStatus lifecycleStatus;
    private RegisteredAddress registeredAddress;

    public Company(
            CompanyID id,
            CompanyOfficialName officialName,
            CompanyCommercialName commercialName,
            CompanyINN inn,
            CompanyKPP kpp,
            CompanyClientSegment clientSegment,
            CompanyLifecycleStatus lifecycleStatus,
            RegisteredAddress registeredAddress) {

        this.id = id;
        this.officialName = officialName;
        this.commercialName = commercialName;
        this.inn = inn;
        this.kpp = kpp;
        this.clientSegment = clientSegment;
        this.lifecycleStatus = lifecycleStatus;
        this.registeredAddress = registeredAddress;
    }

    public static Company createNew(
            CompanyOfficialName officialName,
            CompanyCommercialName commercialName,
            CompanyINN inn,
            CompanyKPP kpp,
            CompanyClientSegment clientSegment,
            RegisteredAddress registeredAddress) {
        return new Company(
                CompanyID.generateID(),
                officialName,
                commercialName,
                inn,
                kpp,
                clientSegment,
                new CompanyLifecycleStatus(CompanyLifecycleStatusCode.PROSPECT),
                registeredAddress);

    }

    public void changeLifecycle(CompanyLifecycleStatus lifecycleStatus) {
        Consumer<CompanyLifecycleStatus> changeLifecycle =
                it -> this.lifecycleStatus = it;

        switch (lifecycleStatus.code()) {
            case CompanyLifecycleStatusCode.PROSPECT -> {
                throw new CompanyIllegalLifecycleChangeException(String.format(
                        "Компании нельзя установить жизненный цикл '%s'",
                        lifecycleStatus.code().getDescription()));
            }

            case CompanyLifecycleStatusCode.ACTIVE -> {
                if (this.lifecycleStatus.code() == CompanyLifecycleStatusCode.ARCHIVED) {
                    throw new CompanyIllegalLifecycleChangeException(String.format(
                            "Из статуса '%s' для компании невозможно установить статус '%s'",
                            CompanyLifecycleStatusCode.ARCHIVED.getDescription(),
                            lifecycleStatus.code().getDescription()));
                }

                changeLifecycle.accept(lifecycleStatus);
            }

            case CompanyLifecycleStatusCode.ARCHIVED -> {
                if (this.lifecycleStatus.code() == CompanyLifecycleStatusCode.ACTIVE) {
                    throw new CompanyIllegalLifecycleChangeException(String.format(
                            "Из статуса '%s' для компании невозможно установить статус '%s'",
                            CompanyLifecycleStatusCode.ACTIVE.getDescription(),
                            lifecycleStatus.code().getDescription()));
                }

                changeLifecycle.accept(lifecycleStatus);
            }

            case CompanyLifecycleStatusCode.INACTIVE -> {
                changeLifecycle.accept(lifecycleStatus);
            }

            default -> throw  new CompanyIllegalLifecycleStatusException(
                    String.format("Неизвестный жизненный цикл компании '%s'", lifecycleStatus.code()));
        }
    }
}
