package fqw.crmprojectbackend.deal.domain.model.client;

import fqw.crmprojectbackend.deal.domain.exception.DealIllegalClientException;
import lombok.Getter;

import java.util.UUID;

@Getter
public class DealClient {
    private final DealClientType type;
    private final DealIndividualID individualID;
    private final DealCompanyID companyID;

    private DealClient(
            DealClientType type,
            DealIndividualID individualID,
            DealCompanyID companyID) {
        this.type = type;
        this.individualID = individualID;
        this.companyID = companyID;
        this.checkClientReference();
    }

    public static DealClient forIndividual(UUID individualID) {
        return new DealClient(
                new DealClientType(DealClientTypeCode.INDIVIDUAL),
                DealIndividualID.from(individualID),
                null);
    }

    public static DealClient forCompany(UUID companyID) {
        return new DealClient(
                new DealClientType(DealClientTypeCode.COMPANY),
                null,
                DealCompanyID.from(companyID));
    }

    private void checkClientReference() {
        if (this.type == null) {
            throw new DealIllegalClientException("Тип клиента сделки должен быть указан");
        }

        if (this.type.code() == DealClientTypeCode.INDIVIDUAL
                && (this.individualID == null || this.companyID != null)) {
            throw new DealIllegalClientException(
                    "Сделка с физ. лицом должна ссылаться только на одно физ. лицо");
        }

        if (this.type.code() == DealClientTypeCode.COMPANY
                && (this.companyID == null || this.individualID != null)) {
            throw new DealIllegalClientException(
                    "Сделка с компанией должна ссылаться только на одну компанию");
        }
    }
}
