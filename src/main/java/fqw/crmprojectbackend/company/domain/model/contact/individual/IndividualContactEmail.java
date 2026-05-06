package fqw.crmprojectbackend.company.domain.model.contact.individual;

import fqw.crmprojectbackend.common.validation.EmailValidator;
import fqw.crmprojectbackend.individual.domain.exception.IndividualInvalidEmailException;

import java.util.Objects;
import java.util.regex.Pattern;

public record IndividualContactEmail(String value) {
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EmailValidator.EMAIL_REGEXP);

    public IndividualContactEmail {
        Objects.requireNonNull(value);

        if (!EMAIL_PATTERN.matcher(value).matches()) {
            throw new IndividualInvalidEmailException("Не верный формат Email");
        }
    }
}
