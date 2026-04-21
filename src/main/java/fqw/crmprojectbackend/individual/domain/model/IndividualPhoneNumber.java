package fqw.crmprojectbackend.individual.domain.model;

import fqw.crmprojectbackend.common.validation.PhoneNumberValidator;
import fqw.crmprojectbackend.individual.domain.exception.IndividualInvalidPhoneException;

import java.util.Objects;
import java.util.regex.Pattern;

public record IndividualPhoneNumber(String value) {
    public IndividualPhoneNumber {
        Objects.requireNonNull(value);

        var pattern = Pattern.compile(PhoneNumberValidator.PHONE_NUMBER_REGEX);
        if (!pattern.matcher(value).matches()) {
            throw new IndividualInvalidPhoneException("Не верный формат номера телефона");
        }
    }
}
