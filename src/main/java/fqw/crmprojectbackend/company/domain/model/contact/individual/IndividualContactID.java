package fqw.crmprojectbackend.company.domain.model.contact.individual;

import fqw.crmprojectbackend.company.domain.model.contact.CompanyContactID;
import lombok.Getter;

import java.util.Objects;
import java.util.UUID;

@Getter
public class IndividualContactID {
    private final UUID value;

    private IndividualContactID(UUID value) {
        Objects.requireNonNull(value);
        this.value = value;
    }

    public static IndividualContactID from (UUID id) {
        return new IndividualContactID(id);
    }
}
