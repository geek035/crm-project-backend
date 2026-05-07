package fqw.crmprojectbackend.deal.adapter.in.web.request;

import jakarta.validation.constraints.NotBlank;

public record DealChangeStageDTO(
        @NotBlank(message = "Код стадии сделки должен быть указан")
        String stageCode,
        String closeInfo
) {
}
