package fqw.crmprojectbackend.company.domain.model.company;

import fqw.crmprojectbackend.company.application.dto.RegisteredAddressDTO;
import fqw.crmprojectbackend.company.domain.exception.company.CompanyIllegalLifecycleChangeException;
import fqw.crmprojectbackend.company.domain.exception.company.CompanyIllegalLifecycleStatusException;
import fqw.crmprojectbackend.company.domain.model.adress.*;
import fqw.crmprojectbackend.company.domain.model.contact.CompanyContact;
import fqw.crmprojectbackend.company.domain.model.contact.CompanyContactRole;
import fqw.crmprojectbackend.company.domain.model.contact.individual.IndividualContact;
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

    public CompanyContact addContact(
            IndividualContact individualContact,
            CompanyContactRole contactRole) {
        return CompanyContact.createNew(individualContact, contactRole);
    }

    public void setOfficialName(String officialName) {
        this.officialName = new CompanyOfficialName(officialName);
    }

    public void setCommercialName(String commercialName) {
        this.commercialName = new CompanyCommercialName(commercialName);
    }

    public void setINN(String inn) {
        this.inn = new CompanyINN(inn);
    }

    public void setKPP(String kpp) {
        this.kpp = new CompanyKPP(kpp);
    }

    public void changeClientSegment(String clientSegmentCode) {
        this.clientSegment = new CompanyClientSegment(CompanyClientSegmentCode.getByCode(clientSegmentCode));
    }

    public void updateRegisteredAddress(RegisteredAddressDTO origin) {
        this.registeredAddress = new RegisteredAddress(
                new RegisteredAddressCountry(origin.country()),
                new RegisteredAddressRegion(origin.region()),
                new RegisteredAddressCity(origin.city()),
                new RegisteredAddressStreet(origin.street()),
                new RegisteredAddressBuilding(origin.building()),
                new RegisteredAddressOffice(origin.office()),
                new RegisteredAddressPostalCode(origin.postalCode()));
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
