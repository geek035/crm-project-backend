package fqw.crmprojectbackend.individual.adapter.in.web.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import fqw.crmprojectbackend.common.validation.EmailValidator;
import fqw.crmprojectbackend.common.validation.PhoneNumberValidator;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record IndividualUpdateDTO(
        @NotNull(message = "Имя не должно быть пустым")
        String firstName,
        @NotNull(message = "Фамилия не должна быть пустой")
        String secondName,
        String surname,
        @NotNull(message = "email не должен быть пустым")
        @Email(regexp = EmailValidator.EMAIL_REGEXP)
        String email,
        @NotNull(message = "Номер телефона не должен быть пустым")
        @Pattern(
                regexp = PhoneNumberValidator.PHONE_NUMBER_REGEX,
                message = "Неверный формат номера телефона"
        )
        String phoneNumber,
        @NotNull(message = "Дата рождения не должна быть пустой")
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate birthdate
) {}
