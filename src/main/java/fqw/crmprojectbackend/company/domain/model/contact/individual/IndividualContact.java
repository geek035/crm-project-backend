package fqw.crmprojectbackend.company.domain.model.contact.individual;

import lombok.Data;

@Data
public class IndividualContact {
    private final IndividualContactID id;
    private final IndividualContactFullName fullName;
    private final IndividualContactEmail email;
    private final IndividualContactPhoneNumber phoneNumber;
}
