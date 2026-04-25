package fqw.crmprojectbackend.company.domain.model.contact;

import fqw.crmprojectbackend.company.domain.model.contact.individual.IndividualContact;
import lombok.Data;

@Data
public class CompanyContact {
    private final CompanyContactID id;
    private final IndividualContact individualContact;
    private final CompanyContactRole role;
    private final CompanyContactStatus status;
}
