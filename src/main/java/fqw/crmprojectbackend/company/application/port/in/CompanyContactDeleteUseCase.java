package fqw.crmprojectbackend.company.application.port.in;

import java.util.UUID;

public interface CompanyContactDeleteUseCase {
    void delete(UUID id);
}
