package fqw.crmprojectbackend.deal.adapter.out.acl;

import fqw.crmprojectbackend.company.application.port.in.CompanyQueryUseCase;
import fqw.crmprojectbackend.deal.application.port.out.DealClientGateway;
import fqw.crmprojectbackend.individual.application.port.in.IndividualQueryUseCase;
import fqw.crmprojectbackend.individual.application.query.IndividualByIDQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DealClientACLAdapter implements DealClientGateway {
    private final IndividualQueryUseCase individualQueryUseCase;
    private final CompanyQueryUseCase companyQueryUseCase;

    @Override
    public boolean existsIndividualByID(UUID id) {
        return this.individualQueryUseCase
                .findById(new IndividualByIDQuery(id))
                .isPresent();
    }

    @Override
    public boolean existsCompanyByID(UUID id) {
        return this.companyQueryUseCase.existsByID(id);
    }
}
