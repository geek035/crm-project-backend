package fqw.crmprojectbackend.individual.adapter.in.web.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import fqw.crmprojectbackend.common.validator.EmailValidator;
import fqw.crmprojectbackend.common.validator.PhoneNumberValidator;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record IndividualAddRequest(
        @NotNull
        String firstName,

        @NotNull
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
        @JsonFormat(pattern = "dd-MM-yyyy")
        LocalDate birthdate
) {}
