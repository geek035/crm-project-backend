package fqw.crmprojectbackend.deal.domain.model.process;

import fqw.crmprojectbackend.deal.domain.exception.DealIllegalStageException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DealStageCode {
    LEAD("Lead"),
    QUALIFICATION("Qualification"),
    OFFER_PREPARATION("Offer preparation"),
    CLIENT_APPROVAL("Client approval"),
    BANK_REVIEW("Bank review"),
    DOCUMENT_SIGNING("Document signing"),
    WON("Won"),
    LOST("Lost"),
    CANCELLED("Cancelled");

    private final String description;

    public static DealStageCode getByCode(String code) {
        return switch (code) {
            case "LEAD" -> DealStageCode.LEAD;
            case "QUALIFICATION" -> DealStageCode.QUALIFICATION;
            case "OFFER_PREPARATION" -> DealStageCode.OFFER_PREPARATION;
            case "CLIENT_APPROVAL" -> DealStageCode.CLIENT_APPROVAL;
            case "BANK_REVIEW" -> DealStageCode.BANK_REVIEW;
            case "DOCUMENT_SIGNING" -> DealStageCode.DOCUMENT_SIGNING;
            case "WON" -> DealStageCode.WON;
            case "LOST" -> DealStageCode.LOST;
            case "CANCELLED" -> DealStageCode.CANCELLED;
            default -> throw new DealIllegalStageException(
                    String.format("Неизвестная стадия сделки '%s'", code));
        };
    }

    public boolean isFinal() {
        return this == WON || this == LOST || this == CANCELLED;
    }
}
