package fqw.crmprojectbackend.deal.application.service;

import fqw.crmprojectbackend.deal.application.command.DealCreateCommand;
import fqw.crmprojectbackend.deal.application.mapper.DealApplicationMapper;
import fqw.crmprojectbackend.deal.application.port.in.DealCreateUseCase;
import fqw.crmprojectbackend.deal.application.port.out.DealClientGateway;
import fqw.crmprojectbackend.deal.application.port.out.DealRepositoryPort;
import fqw.crmprojectbackend.deal.domain.exception.DealClientNotFoundException;
import fqw.crmprojectbackend.deal.domain.exception.DealDuplicateNumberException;
import fqw.crmprojectbackend.deal.domain.model.client.DealClient;
import fqw.crmprojectbackend.deal.domain.model.client.DealClientTypeCode;
import fqw.crmprojectbackend.deal.domain.model.deal.Deal;
import fqw.crmprojectbackend.deal.domain.model.deal.DealDescription;
import fqw.crmprojectbackend.deal.domain.model.deal.DealNumber;
import fqw.crmprojectbackend.deal.domain.model.deal.DealTitle;
import fqw.crmprojectbackend.deal.domain.model.money.DealAmount;
import fqw.crmprojectbackend.deal.domain.model.process.DealPriority;
import fqw.crmprojectbackend.deal.domain.model.process.DealPriorityCode;
import fqw.crmprojectbackend.deal.domain.model.process.DealSource;
import fqw.crmprojectbackend.deal.domain.model.process.DealSourceCode;
import fqw.crmprojectbackend.deal.domain.model.product.DealProduct;
import fqw.crmprojectbackend.deal.domain.model.product.DealProductCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DealCreateService implements DealCreateUseCase {
    private final DealRepositoryPort dealRepositoryPort;
    private final DealClientGateway dealClientGateway;

    @Override
    @Transactional
    public UUID create(DealCreateCommand command) {
        var number = new DealNumber(command.number());

        if (this.dealRepositoryPort.existsByNumber(number)) {
            throw new DealDuplicateNumberException(String.format(
                    "Сделка с номером '%s' уже существует",
                    number.value()));
        }

        var clientTypeCode = DealClientTypeCode.getByCode(command.clientTypeCode());
        var client = this.resolveClient(clientTypeCode, command.clientID());

        var deal = Deal.createNew(
                number,
                client,
                new DealTitle(command.title()),
                new DealDescription(command.description()),
                new DealProduct(DealProductCode.getByCode(command.productCode())),
                DealAmount.of(command.amount(), command.currencyCode()),
                new DealPriority(DealPriorityCode.getByCode(command.priorityCode())),
                new DealSource(DealSourceCode.getByCode(command.sourceCode())),
                command.expectedCloseDate());

        return this.dealRepositoryPort.add(DealApplicationMapper.toRequest(deal));
    }

    private DealClient resolveClient(
            DealClientTypeCode clientTypeCode,
            UUID clientID) {
        return switch (clientTypeCode) {
            case INDIVIDUAL -> {
                if (!this.dealClientGateway.existsIndividualByID(clientID)) {
                    throw new DealClientNotFoundException(String.format(
                            "Физ. лицо с идентификатором '%s' не существует",
                            clientID));
                }

                yield DealClient.forIndividual(clientID);
            }

            case COMPANY -> {
                if (!this.dealClientGateway.existsCompanyByID(clientID)) {
                    throw new DealClientNotFoundException(String.format(
                            "Компания с идентификатором '%s' не существует",
                            clientID));
                }

                yield DealClient.forCompany(clientID);
            }
        };
    }
}
