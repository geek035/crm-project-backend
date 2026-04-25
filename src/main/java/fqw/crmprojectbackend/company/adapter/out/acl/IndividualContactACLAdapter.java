package fqw.crmprojectbackend.company.adapter.out.acl;

import fqw.crmprojectbackend.company.application.port.out.IndividualContactGateway;
import fqw.crmprojectbackend.company.domain.model.contact.individual.*;
import fqw.crmprojectbackend.individual.application.port.in.IndividualQueryUseCase;
import fqw.crmprojectbackend.individual.application.query.IndividualByIDQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IndividualContactACLAdapter implements IndividualContactGateway {
    private final IndividualQueryUseCase individualQueryUseCase;

    @Override
    public Optional<IndividualContact> getByID(IndividualContactID id) {
        var individual = individualQueryUseCase.findById(new IndividualByIDQuery(id.getValue()));

        return individual.map(it -> new IndividualContact(
                IndividualContactID.from(it.id()),
                new IndividualContactFullName(
                        it.firstName(),
                        it.secondName(),
                        it.surname()),
                new IndividualContactEmail(it.email()),
                new IndividualContactPhoneNumber(it.phoneNumber())
        ));
    }
}
