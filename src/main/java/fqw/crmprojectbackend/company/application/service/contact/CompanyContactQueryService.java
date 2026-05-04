package fqw.crmprojectbackend.company.application.service.contact;

import fqw.crmprojectbackend.common.query.BaseQueryParams;
import fqw.crmprojectbackend.company.application.dto.CompanyContactDTO;
import fqw.crmprojectbackend.company.application.dto.CompanyContactPageDTO;
import fqw.crmprojectbackend.company.application.port.in.CompanyContactQueryUseCase;
import fqw.crmprojectbackend.company.application.port.out.CompanyContactRepositoryPort;
import fqw.crmprojectbackend.company.domain.exception.contact.CompanyContactNotExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CompanyContactQueryService implements CompanyContactQueryUseCase {
    private final CompanyContactRepositoryPort contactRepositoryPort;

    @Override
    public CompanyContactDTO findById(UUID id) {
        return this.contactRepositoryPort.findByID(id)
                .orElseThrow(() -> new CompanyContactNotExistsException(
                        String.format(
                                "Не удалось найти контакт с идентификатором '%s'",
                                id)));
    }

    @Override
    public CompanyContactPageDTO findByParams(UUID companyID, BaseQueryParams params) {
        var total = this.contactRepositoryPort.getTotal();
        var data = this.contactRepositoryPort.findByParams(companyID, params);

        return new CompanyContactPageDTO(total, data);
    }
}
