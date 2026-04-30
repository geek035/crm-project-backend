package fqw.crmprojectbackend.company.application.port.out;

import fqw.crmprojectbackend.company.application.request.CompanyContactAddRequest;

import java.util.Optional;
import java.util.UUID;

public interface CompanyContactRepositoryPort {
    UUID addContact(CompanyContactAddRequest request);

    boolean existByIndividualID(UUID individualID);
}
