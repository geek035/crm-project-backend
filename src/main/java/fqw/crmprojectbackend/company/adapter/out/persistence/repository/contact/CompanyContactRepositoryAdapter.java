package fqw.crmprojectbackend.company.adapter.out.persistence.repository.contact;

import fqw.crmprojectbackend.company.adapter.out.persistence.entity.company.CompanyJPAEntity;
import fqw.crmprojectbackend.company.adapter.out.persistence.entity.contact.CompanyContactJPAEntity;
import fqw.crmprojectbackend.company.adapter.out.persistence.entity.contact.CompanyContactRoleJPAEntity;
import fqw.crmprojectbackend.company.adapter.out.persistence.entity.contact.CompanyContactStatusJPAEntity;
import fqw.crmprojectbackend.company.application.port.out.CompanyContactRepositoryPort;
import fqw.crmprojectbackend.company.application.request.CompanyContactAddRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class CompanyContactRepositoryAdapter implements CompanyContactRepositoryPort {
    private final CompanyContactSpringDataRepository contactSpringDataRepository;

    @Override
    public boolean existByIndividualID(UUID individualID) {
        return this.contactSpringDataRepository.existsByIndividualID(individualID);
    }

    @Override
    public UUID addContact(CompanyContactAddRequest request) {
        var contact = new CompanyContactJPAEntity();
        contact.setId(request.id());
        contact.setIndividualID(request.individualID());

        var company = new CompanyJPAEntity();
        company.setId(request.companyID());
        contact.setCompany(company);

        var role = new CompanyContactRoleJPAEntity();
        role.setCode(request.roleCode());
        contact.setRole(role);

        var status = new CompanyContactStatusJPAEntity();
        status.setCode(request.statusCode());
        contact.setStatus(status);

        var saved = this.contactSpringDataRepository.save(contact);

        return saved.getId();
    }
}
