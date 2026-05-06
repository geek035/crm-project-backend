package fqw.crmprojectbackend.deal.adapter.in.web.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record DealCreateDTO(
        @NotBlank(message = "Номер сделки должен быть указан")
        String number,

        @NotBlank(message = "Тип клиента сделки должен быть указан")
        String clientTypeCode,

        @NotNull(message = "Идентификатор клиента сделки должен быть указан")
        UUID clientID,

        @NotBlank(message = "Название сделки должно быть указано")
        String title,

        String description,

        @NotBlank(message = "Код продукта сделки должен быть указан")
        String productCode,

        @NotNull(message = "Сумма сделки должна быть указана")
        @DecimalMin(value = "0.01", message = "Сумма сделки должна быть больше нуля")
        BigDecimal amount,

        @NotBlank(message = "Код валюты сделки должен быть указан")
        String currencyCode,

        @NotBlank(message = "Приоритет сделки должен быть указан")
        String priorityCode,

        @NotBlank(message = "Источник сделки должен быть указан")
        String sourceCode,

        LocalDate expectedCloseDate) {
}
