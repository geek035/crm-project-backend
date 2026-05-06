package fqw.crmprojectbackend.deal.domain.model.product;

import fqw.crmprojectbackend.deal.domain.exception.DealIllegalProductException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DealProductCode {
    CURRENT_ACCOUNT("Current account"),
    LOAN("Loan"),
    DEPOSIT("Deposit"),
    SALARY_PROJECT("Salary project"),
    ACQUIRING("Acquiring"),
    BANK_GUARANTEE("Bank guarantee"),
    LEASING("Leasing");

    private final String description;

    public static DealProductCode getByCode(String code) {
        return switch (code) {
            case "CURRENT_ACCOUNT" -> DealProductCode.CURRENT_ACCOUNT;
            case "LOAN" -> DealProductCode.LOAN;
            case "DEPOSIT" -> DealProductCode.DEPOSIT;
            case "SALARY_PROJECT" -> DealProductCode.SALARY_PROJECT;
            case "ACQUIRING" -> DealProductCode.ACQUIRING;
            case "BANK_GUARANTEE" -> DealProductCode.BANK_GUARANTEE;
            case "LEASING" -> DealProductCode.LEASING;
            default -> throw new DealIllegalProductException(
                    String.format("Неизвестный продукт сделки '%s'", code));
        };
    }
}
