package fqw.crmprojectbackend.common.query;

import jakarta.validation.constraints.NotNull;

public record BaseQueryFilterDTO(
        @NotNull
        String field,
        @NotNull
        Object value,
        @NotNull
        String matchMode
) {
}
