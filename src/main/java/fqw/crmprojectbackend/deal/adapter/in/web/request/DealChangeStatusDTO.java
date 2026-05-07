package fqw.crmprojectbackend.deal.adapter.in.web.request;

import jakarta.validation.constraints.NotBlank;

public record DealChangeStatusDTO(
        @NotBlank(message = "Код статуса сделки должен быть указан")
        String statusCode,
        String closeInfo
) {
}
