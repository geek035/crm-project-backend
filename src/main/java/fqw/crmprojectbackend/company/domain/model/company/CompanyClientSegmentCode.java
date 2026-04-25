package fqw.crmprojectbackend.company.domain.model.company;

import fqw.crmprojectbackend.company.domain.exception.CompanyIllegalClientSegmentException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CompanyClientSegmentCode {
    MICRO("Микробизнес"),
    SMALL_BUSINESS("Малый бизнес"),
    MEDIUM_BUSINESS("Средний бизнес"),
    PARTNER("Партнер");


    private final String description;

    public static CompanyClientSegmentCode getByCode(String code) {
        return switch (code) {
            case "MICRO" -> CompanyClientSegmentCode.MICRO;
            case "SMALL_BUSINESS" -> CompanyClientSegmentCode.SMALL_BUSINESS;
            case "MEDIUM_BUSINESS" -> CompanyClientSegmentCode.MEDIUM_BUSINESS;
            case "PARTNER" -> CompanyClientSegmentCode.PARTNER;
            default ->  throw new CompanyIllegalClientSegmentException(
                    String.format("Неизвестный сегмент деятельности компании '%s'", code));
        };
    }
}
