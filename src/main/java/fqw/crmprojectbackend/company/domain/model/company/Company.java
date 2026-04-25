package fqw.crmprojectbackend.company.domain.model.company;

import fqw.crmprojectbackend.company.domain.model.adress.RegisteredAddress;
import fqw.crmprojectbackend.company.domain.model.contact.CompanyContact;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Company {
    private final CompanyID id;
    private final CompanyOfficialName officialName;
    private final CompanyCommercialName commercialName;
    private final CompanyINN inn;
    private final CompanyKPP kpp;
    private final CompanyClientSegment clientSegment;
    private final CompanyLifecycleStatus lifecycleStatus;
    private final RegisteredAddress registeredAddress;
    private final List<CompanyContact> contacts;

    public Company(
            CompanyID id,
            CompanyOfficialName officialName,
            CompanyCommercialName commercialName,
            CompanyINN inn,
            CompanyKPP kpp,
            CompanyClientSegment clientSegment,
            CompanyLifecycleStatus lifecycleStatus,
            RegisteredAddress registeredAddress,
            List<CompanyContact> contacts) {

        this.id = id;
        this.officialName = officialName;
        this.commercialName = commercialName;
        this.inn = inn;
        this.kpp = kpp;
        this.clientSegment = clientSegment;
        this.lifecycleStatus = lifecycleStatus;
        this.registeredAddress = registeredAddress;
        this.contacts = contacts;
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
                new CompanyLifecycleStatus(CompanyLifecycleStatusType.ACTIVE),
                registeredAddress,
                new ArrayList<>());

    }
}
