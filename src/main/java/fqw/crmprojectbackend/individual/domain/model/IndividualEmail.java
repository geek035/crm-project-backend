package fqw.crmprojectbackend.individual.domain.model;

import fqw.crmprojectbackend.common.validator.EmailValidator;
import fqw.crmprojectbackend.individual.domain.exception.IndividualInvalidEmailException;

import java.util.Objects;
import java.util.regex.Pattern;

public record IndividualEmail(String value) {
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EmailValidator.EMAIL_REGEXP);

    public IndividualEmail {
        Objects.requireNonNull(value);

        if (!EMAIL_PATTERN.matcher(value).matches()) {
            throw new IndividualInvalidEmailException("Не верный формат Email");
        }
    }
}
