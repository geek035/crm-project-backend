package fqw.crmprojectbackend.company.application.mapper;

import fqw.crmprojectbackend.company.application.request.CompanyContactAddRequest;
import fqw.crmprojectbackend.company.domain.model.company.CompanyID;
import fqw.crmprojectbackend.company.domain.model.contact.CompanyContact;

public class CompanyContactApplicationMapper {
    public static CompanyContactAddRequest toRequest(CompanyID id, CompanyContact contact) {
        return new CompanyContactAddRequest(
                contact.getId().getValue(),
                id.getValue(),
                contact.getIndividualContact().getId().getValue(),
                contact.getRole().code().name(),
                contact.getStatus().code().name());
    }
}
