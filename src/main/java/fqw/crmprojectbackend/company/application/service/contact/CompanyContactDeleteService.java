package fqw.crmprojectbackend.company.application.service.contact;

import fqw.crmprojectbackend.company.application.port.in.CompanyContactDeleteUseCase;
import fqw.crmprojectbackend.company.application.port.out.CompanyContactRepositoryPort;
import fqw.crmprojectbackend.company.domain.exception.contact.CompanyContactNotExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CompanyContactDeleteService implements CompanyContactDeleteUseCase {
    private final CompanyContactRepositoryPort contactRepositoryPort;

    @Override
    public void delete(UUID id) {
        var isExists = this.contactRepositoryPort.existsByID(id);

        if (!isExists) {
            throw new CompanyContactNotExistsException(String.format(
                    "Контакта с идентификатором '%s' не существует",
                    id));
        }

        this.contactRepositoryPort.delete(id);
    }
}
