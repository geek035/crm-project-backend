package fqw.crmprojectbackend.company.application.service.company;

import fqw.crmprojectbackend.company.application.command.CompanyUpdateCommand;
import fqw.crmprojectbackend.company.application.command.CompanyUpdateLifecycleCommand;
import fqw.crmprojectbackend.company.application.dto.CompanyDTO;
import fqw.crmprojectbackend.company.application.mapper.CompanyApplicationMapper;
import fqw.crmprojectbackend.company.application.port.in.CompanyUpdateUseCase;
import fqw.crmprojectbackend.company.application.port.out.CompanyRepositoryPort;
import fqw.crmprojectbackend.company.domain.exception.company.CompanyDuplicateINNException;
import fqw.crmprojectbackend.company.domain.exception.company.CompanyNotExistsException;
import fqw.crmprojectbackend.company.domain.model.company.CompanyID;
import fqw.crmprojectbackend.company.domain.model.company.CompanyINN;
import fqw.crmprojectbackend.company.domain.model.company.CompanyLifecycleStatus;
import fqw.crmprojectbackend.company.domain.model.company.CompanyLifecycleStatusCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CompanyUpdateService implements CompanyUpdateUseCase {
    private final CompanyRepositoryPort companyRepositoryPort;

    @Override
    @Transactional
    public CompanyDTO update(UUID id, CompanyUpdateCommand command) {
        var companyID = CompanyID.from(id);
        var companyINN = new CompanyINN(command.inn());
        var company = this.companyRepositoryPort
                .findByID(companyID)
                .orElseThrow(() -> new CompanyNotExistsException(String.format(
                        "Компании с идентификатором '%s' не существует",
                        id)));

        var sameInnCompany = this.companyRepositoryPort.findByINN(companyINN);
        if (sameInnCompany.isPresent() && !sameInnCompany.get().getId().getValue().equals(id)) {
            throw new CompanyDuplicateINNException(String.format(
                    "Компании с ИНН '%s' уже существует",
                    companyINN.value()));
        }

        company.setOfficialName(command.officialName());
        company.setCommercialName(command.commercialName());
        company.setINN(command.inn());
        company.setKPP(command.kpp());
        company.changeClientSegment(command.clientSegmentCode());
        company.updateRegisteredAddress(command.registeredAddress());

        return this.companyRepositoryPort
                .updateByOrigin(CompanyApplicationMapper.fromDomainModel(company));
    }

    @Override
    @Transactional
    public CompanyDTO updateLifecycle(UUID id, CompanyUpdateLifecycleCommand command) {
        var companyOptional = this.companyRepositoryPort.findByID(CompanyID.from(id));

        if (companyOptional.isEmpty()) {
            throw new CompanyNotExistsException(String.format(
                    "Компании с идентификатором '%s' не существует",
                    id));
        }

        var company = companyOptional.get();
        var lifecycleStatus = new CompanyLifecycleStatus(
                CompanyLifecycleStatusCode.getByCode(command.lifecycleCode()));

        company.changeLifecycle(lifecycleStatus);

        return this.companyRepositoryPort
                .updateByOrigin(CompanyApplicationMapper.fromDomainModel(company));
    }
}
