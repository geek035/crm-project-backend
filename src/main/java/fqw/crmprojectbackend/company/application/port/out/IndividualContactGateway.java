package fqw.crmprojectbackend.company.application.port.out;

import fqw.crmprojectbackend.company.domain.model.contact.individual.IndividualContact;
import fqw.crmprojectbackend.company.domain.model.contact.individual.IndividualContactID;

import java.util.*;

public interface IndividualContactGateway {
    Optional<IndividualContact> getByID(IndividualContactID id);
    Map<UUID, IndividualContact> getByIDs(Collection<UUID> ids);
}
