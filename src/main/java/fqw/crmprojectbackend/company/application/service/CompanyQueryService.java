package fqw.crmprojectbackend.company.application.service;

import fqw.crmprojectbackend.company.application.dto.CompanyDTO;
import fqw.crmprojectbackend.company.application.dto.CompanyPageDTO;
import fqw.crmprojectbackend.company.application.mapper.CompanyApplicationMapper;
import fqw.crmprojectbackend.company.application.port.in.CompanyQueryUseCase;
import fqw.crmprojectbackend.company.application.port.out.CompanyRepositoryPort;
import fqw.crmprojectbackend.company.application.query.CompanyQueryParams;
import fqw.crmprojectbackend.company.domain.exception.company.CompanyNotExistsException;
import fqw.crmprojectbackend.company.domain.model.company.CompanyID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CompanyQueryService implements CompanyQueryUseCase {
    private final CompanyRepositoryPort companyRepositoryPort;

    @Override
    public CompanyDTO findById(UUID id) {
        var company = this.companyRepositoryPort.findByID(CompanyID.from(id))
                .orElseThrow(() -> new CompanyNotExistsException(String.format(
                        "Компании с id '%s' не существует", id)));

        return CompanyApplicationMapper.fromDomainModel(company);
    }

    @Override
    public CompanyPageDTO findByParams(CompanyQueryParams params) {
        var data = this.companyRepositoryPort.findByParams(params).stream()
                .map(CompanyApplicationMapper::fromDomainModel)
                .toList();

        var total = this.companyRepositoryPort.getTotal();

        return new CompanyPageDTO(total, data);
    }
}
