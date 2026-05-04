package fqw.crmprojectbackend.company.domain.model.contact;

import fqw.crmprojectbackend.company.domain.model.contact.individual.IndividualContact;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CompanyContact {
    private CompanyContactID id;
    private IndividualContact individualContact;
    private CompanyContactRole role;
    private CompanyContactStatus status;

    public static CompanyContact createNew(
            IndividualContact contact,
            CompanyContactRole role) {
        var id = CompanyContactID.generateID();
        var status = new CompanyContactStatus(CompanyContactStatusCode.ACTIVE);

        return new CompanyContact(
                id,
                contact,
                role,
                status);
    }

    public void updateRole(CompanyContactRoleCode code) {
        this.role = new CompanyContactRole(code);
    }

}
