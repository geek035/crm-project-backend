package fqw.crmprojectbackend.company.domain.model.contact;

import fqw.crmprojectbackend.company.domain.exception.contact.CompanyContactIllegalStatusChangeException;
import fqw.crmprojectbackend.company.domain.exception.contact.CompanyContactIllegalStatusException;
import fqw.crmprojectbackend.company.domain.model.contact.individual.IndividualContact;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.function.Consumer;

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

    public void changeRole(CompanyContactRoleCode code) {
        this.role = new CompanyContactRole(code);
    }

    public void changeStatus(CompanyContactStatusCode code) {
        Consumer<CompanyContactStatusCode> statusChanger =
                it ->  this.status = new CompanyContactStatus(it);

        switch (code) {
            case CompanyContactStatusCode.ACTIVE,
                 CompanyContactStatusCode.INACTIVE,
                 CompanyContactStatusCode.TEMPORARY_UNAVAILABLE -> {
                this.checkDismissedStatus(code);
                statusChanger.accept(code);
            }

            case CompanyContactStatusCode.DISMISSED -> statusChanger.accept(code);

            default -> throw new CompanyContactIllegalStatusException(String.format(
                    "Неизвестный статус контакта с кодом '%s'",
                    code.name()));
        }
    }

    private void checkDismissedStatus(CompanyContactStatusCode code) {
        if (this.status.code() == CompanyContactStatusCode.DISMISSED) {
            throw new CompanyContactIllegalStatusChangeException(String.format(
                    "Из статуса '%s' для контакта невозможно установить статус '%s'",
                    this.status.code().getDescription(),
                    code.getDescription()));
        }
    }

}
