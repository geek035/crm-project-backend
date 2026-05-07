package fqw.crmprojectbackend.deal.domain.model.process;

import fqw.crmprojectbackend.deal.domain.exception.DealIllegalLossReasonException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DealLossReasonCode {
    CLIENT_REFUSED("Клиент отказался"),
    BANK_REJECTED("Банк отказал"),
    CONDITIONS_NOT_ACCEPTED("Условия не согласованы"),
    COMPETITOR("Выбран конкурент"),
    DUPLICATE("Дубль сделки"),
    CANCELLED_BY_CLIENT("Отменена клиентом"),
    OTHER("Другая причина");

    private final String description;

    public static DealLossReasonCode getByCode(String code) {
        return switch (code) {
            case "CLIENT_REFUSED" -> DealLossReasonCode.CLIENT_REFUSED;
            case "BANK_REJECTED" -> DealLossReasonCode.BANK_REJECTED;
            case "CONDITIONS_NOT_ACCEPTED" -> DealLossReasonCode.CONDITIONS_NOT_ACCEPTED;
            case "COMPETITOR" -> DealLossReasonCode.COMPETITOR;
            case "DUPLICATE" -> DealLossReasonCode.DUPLICATE;
            case "CANCELLED_BY_CLIENT" -> DealLossReasonCode.CANCELLED_BY_CLIENT;
            case "OTHER" -> DealLossReasonCode.OTHER;
            default -> throw new DealIllegalLossReasonException(
                    String.format("Неизвестная причина проигрыша сделки '%s'", code));
        };
    }
}
