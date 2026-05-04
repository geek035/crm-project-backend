package fqw.crmprojectbackend.company.adapter.out.persistence.mapper;

import fqw.crmprojectbackend.common.dto.DirectoryEntryDTO;
import fqw.crmprojectbackend.company.adapter.out.persistence.entity.company.CompanyClientSegmentJPAEntity;
import fqw.crmprojectbackend.company.adapter.out.persistence.entity.company.CompanyJPAEntity;
import fqw.crmprojectbackend.company.adapter.out.persistence.entity.company.CompanyLifecycleStatusJPAEntity;
import fqw.crmprojectbackend.company.adapter.out.persistence.entity.contact.CompanyContactJPAEntity;
import fqw.crmprojectbackend.company.application.dto.CompanyDTO;
import fqw.crmprojectbackend.company.application.dto.RegisteredAddressDTO;
import fqw.crmprojectbackend.company.domain.model.company.*;

import java.util.ArrayList;

public class CompanyPersistenceMapper {
    public static CompanyJPAEntity fromDomainModel(Company company) {
        var companyJPA = new CompanyJPAEntity();

        companyJPA.setId(company.getId().getValue());
        companyJPA.setOfficialName(company.getOfficialName().value());
        companyJPA.setCommercialName(company.getCommercialName().value());
        companyJPA.setInn(company.getInn().value());
        companyJPA.setKpp(company.getKpp().value());

        var clientSegmentCode = String.valueOf(company.getClientSegment().code());
        var clientSegmentJPA = new CompanyClientSegmentJPAEntity();
        clientSegmentJPA.setCode(clientSegmentCode);
        companyJPA.setClientSegment(clientSegmentJPA);

        var lifeCycleCode = String.valueOf(company.getLifecycleStatus().code());
        var lifecycleJPA = new CompanyLifecycleStatusJPAEntity();
        lifecycleJPA.setCode(lifeCycleCode);
        companyJPA.setLifecycleStatus(lifecycleJPA);

        var registeredAddressJPA = RegisteredAddressPersistenceMapper.fromDomainModel(
                company.getRegisteredAddress());

        companyJPA.setRegisteredAddress(registeredAddressJPA);

        var contacts = new ArrayList<CompanyContactJPAEntity>();
        companyJPA.setContacts(contacts);

        return companyJPA;
    }

    public static Company toDomainModel(CompanyJPAEntity jpa) {
        return new Company(
                CompanyID.from(jpa.getId()),
                new CompanyOfficialName(jpa.getOfficialName()),
                new CompanyCommercialName(jpa.getCommercialName()),
                new CompanyINN(jpa.getInn()),
                new CompanyKPP(jpa.getKpp()),
                new CompanyClientSegment(
                        CompanyClientSegmentCode.getByCode(jpa.getClientSegment().getCode())),
                new CompanyLifecycleStatus(
                        CompanyLifecycleStatusCode.getByCode(jpa.getLifecycleStatus().getCode())),
                RegisteredAddressPersistenceMapper.toDomainModel(jpa.getRegisteredAddress()));
    }

    public static CompanyDTO fromEntity(CompanyJPAEntity entity) {
        return new CompanyDTO(
                entity.getId(),
                entity.getOfficialName(),
                entity.getCommercialName(),
                entity.getInn(),
                entity.getKpp(),
                new DirectoryEntryDTO(
                        entity.getClientSegment().getCode(),
                        entity.getClientSegment().getDescription()),
                new DirectoryEntryDTO(
                        entity.getLifecycleStatus().getCode(),
                        entity.getLifecycleStatus().getDescription()
                ),
                new RegisteredAddressDTO(
                        entity.getRegisteredAddress().getCountry(),
                        entity.getRegisteredAddress().getRegion(),
                        entity.getRegisteredAddress().getCity(),
                        entity.getRegisteredAddress().getStreet(),
                        entity.getRegisteredAddress().getBuilding(),
                        entity.getRegisteredAddress().getOffice(),
                        entity.getRegisteredAddress().getPostalCode()
                ));
    }
}
