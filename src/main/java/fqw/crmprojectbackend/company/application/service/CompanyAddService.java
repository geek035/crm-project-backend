package fqw.crmprojectbackend.company.application.service;

import fqw.crmprojectbackend.company.application.command.CompanyAddCommand;
import fqw.crmprojectbackend.company.application.mapper.RegisteredAddressApplicationMapper;
import fqw.crmprojectbackend.company.application.port.in.CompanyAddUseCase;
import fqw.crmprojectbackend.company.application.port.out.CompanyRepositoryPort;
import fqw.crmprojectbackend.company.domain.exception.CompanyDuplicateINNException;
import fqw.crmprojectbackend.company.domain.model.company.*;
import fqw.crmprojectbackend.company.domain.model.company.CompanyClientSegment;
import fqw.crmprojectbackend.company.domain.model.company.CompanyClientSegmentCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyAddService implements CompanyAddUseCase {
    private final CompanyRepositoryPort companyRepositoryPort;

    @Override
    @Transactional
    public CompanyID add(CompanyAddCommand command) {
        var inn = new CompanyINN(command.inn());

        if (this.companyRepositoryPort.existsByINN(inn)) {
            throw new CompanyDuplicateINNException(String.format(
                    "Компания с ИНН '%s' уже зарегистрирована", command.inn()));
        }

        var company = Company.createNew(
                new CompanyOfficialName(command.officialName()),
                new CompanyCommercialName(command.commercialName()),
                inn,
                new CompanyKPP(command.kpp()),
                new CompanyClientSegment(CompanyClientSegmentCode.getByCode(command.clientSegmentCode())),
                RegisteredAddressApplicationMapper.toDomainModel(command.registeredAddress()));

        return this.companyRepositoryPort.add(company);
    }
}
