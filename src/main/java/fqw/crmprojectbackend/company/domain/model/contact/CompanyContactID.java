package fqw.crmprojectbackend.company.domain.model.contact;

import fqw.crmprojectbackend.company.domain.model.company.CompanyID;
import lombok.Getter;

import java.util.Objects;
import java.util.UUID;

@Getter
public class CompanyContactID {
    private final UUID value;

    private CompanyContactID(UUID value) {
        Objects.requireNonNull(value);
        this.value = value;
    }

    public static CompanyContactID generateID() {
        return new CompanyContactID(UUID.randomUUID());
    }

    public static CompanyContactID from (UUID id) {
        return new CompanyContactID(id);
    }
}
