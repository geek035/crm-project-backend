package fqw.crmprojectbackend.deal.application.port.out;

import java.util.UUID;

public interface DealClientGateway {
    boolean existsIndividualByID(UUID id);
    boolean existsCompanyByID(UUID id);
}
