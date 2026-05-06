package fqw.crmprojectbackend.deal.domain.model.process;

import fqw.crmprojectbackend.deal.domain.exception.DealIllegalSourceException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DealSourceCode {
    WEBSITE("Website"),
    BRANCH("Branch"),
    CALL_CENTER("Call center"),
    MANAGER_OUTBOUND("Manager outbound"),
    PARTNER("Partner"),
    REFERRAL("Referral"),
    REPEAT_CLIENT("Repeat client");

    private final String description;

    public static DealSourceCode getByCode(String code) {
        return switch (code) {
            case "WEBSITE" -> DealSourceCode.WEBSITE;
            case "BRANCH" -> DealSourceCode.BRANCH;
            case "CALL_CENTER" -> DealSourceCode.CALL_CENTER;
            case "MANAGER_OUTBOUND" -> DealSourceCode.MANAGER_OUTBOUND;
            case "PARTNER" -> DealSourceCode.PARTNER;
            case "REFERRAL" -> DealSourceCode.REFERRAL;
            case "REPEAT_CLIENT" -> DealSourceCode.REPEAT_CLIENT;
            default -> throw new DealIllegalSourceException(
                    String.format("Неизвестный источник сделки '%s'", code));
        };
    }
}
