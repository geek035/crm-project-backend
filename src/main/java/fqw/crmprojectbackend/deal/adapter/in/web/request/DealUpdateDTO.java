package fqw.crmprojectbackend.deal.adapter.in.web.request;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record DealUpdateDTO(
        @NotBlank(message = "Название сделки должно быть указано")
        String title,

        String description,

        @NotBlank(message = "Приоритет сделки должен быть указан")
        String priorityCode,

        @NotBlank(message = "Источник сделки должен быть указан")
        String sourceCode,

        LocalDate expectedCloseDate
) {
}
