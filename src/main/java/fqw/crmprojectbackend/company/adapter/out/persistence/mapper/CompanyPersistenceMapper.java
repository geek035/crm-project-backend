package fqw.crmprojectbackend.company.adapter.out.persistence.mapper;

import fqw.crmprojectbackend.company.adapter.out.persistence.entity.company.CompanyClientSegmentJPAEntity;
import fqw.crmprojectbackend.company.adapter.out.persistence.entity.company.CompanyJPAEntity;
import fqw.crmprojectbackend.company.adapter.out.persistence.entity.company.CompanyLifecycleStatusJPAEntity;
import fqw.crmprojectbackend.company.adapter.out.persistence.entity.contact.CompanyContactJPAEntity;
import fqw.crmprojectbackend.company.domain.model.company.Company;

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
}
