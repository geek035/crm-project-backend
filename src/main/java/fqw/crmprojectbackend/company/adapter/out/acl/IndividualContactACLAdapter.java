package fqw.crmprojectbackend.company.adapter.out.acl;

import fqw.crmprojectbackend.company.application.port.out.IndividualContactGateway;
import fqw.crmprojectbackend.company.domain.model.contact.individual.*;
import fqw.crmprojectbackend.individual.application.dto.IndividualDTO;
import fqw.crmprojectbackend.individual.application.port.in.IndividualQueryUseCase;
import fqw.crmprojectbackend.individual.application.query.IndividualByIDQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IndividualContactACLAdapter implements IndividualContactGateway {
    private final IndividualQueryUseCase individualQueryUseCase;

    @Override
    public Optional<IndividualContact> getByID(IndividualContactID id) {
        var query = new IndividualByIDQuery(id.getValue());
        var individual = individualQueryUseCase.findById(query);

        return individual.map(this::mapToContact);
    }

    @Override
    public Map<UUID, IndividualContact> getByIDs(Collection<UUID> ids) {
        var individuals = this.individualQueryUseCase.findByIDs(ids);

        return individuals
                .stream()
                .map(this::mapToContact)
                .collect(Collectors.toMap(
                                item -> item.getId().getValue(),
                                item -> item));
    }

    private IndividualContact mapToContact(IndividualDTO dto) {
        return new IndividualContact(
                IndividualContactID.from(dto.id()),
                new IndividualContactFullName(
                        dto.firstName(),
                        dto.secondName(),
                        dto.surname()),
                new IndividualContactEmail(dto.email()),
                new IndividualContactPhoneNumber(dto.phoneNumber())
        );
    }
}
