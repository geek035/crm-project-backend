package fqw.crmprojectbackend.individual.adapter.in.web.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import fqw.crmprojectbackend.common.validation.EmailValidator;
import fqw.crmprojectbackend.common.validation.PhoneNumberValidator;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record IndividualAddDTO(
        @NotNull
        @Size(min = 2)
        String firstName,

        @NotNull
        @Size(min = 2)
        String secondName,

        String surname,

        @NotNull
        @Email(regexp = EmailValidator.EMAIL_REGEXP)
        String email,

        @NotNull
        @Pattern(
                regexp = PhoneNumberValidator.PHONE_NUMBER_REGEX,
                message = "Неверный формат номера телефона"
                )
        String phoneNumber,

        @NotNull
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate birthdate
) {}
