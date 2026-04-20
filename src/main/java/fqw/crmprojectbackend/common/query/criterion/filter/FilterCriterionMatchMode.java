package fqw.crmprojectbackend.common.query.criterion.filter;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FilterCriterionMatchMode {
    STARTS_WITH("startsWith"),
    CONTAINS("contains"),
    NOT_CONTAINS("notContains"),
    ENDS_WITH("endsWith"),
    EQUALS("equals"),
    NOT_EQUALS("notEquals");

    private final String matchMode;

    public static FilterCriterionMatchMode getByMatchMode(String mode) {
        return switch (mode) {
            case "startsWith" -> FilterCriterionMatchMode.STARTS_WITH;
            case "contains" -> FilterCriterionMatchMode.CONTAINS;
            case "notContains" -> FilterCriterionMatchMode.NOT_CONTAINS;
            case "endsWith" -> FilterCriterionMatchMode.ENDS_WITH;
            case "equals" -> FilterCriterionMatchMode.EQUALS;
            case "notEquals" -> FilterCriterionMatchMode.NOT_EQUALS;
            default ->  throw new IllegalFilterMatchModeException(
                    String.format("Неизвестный FilterMatchMode '%s'", mode));
        };
    }
}
