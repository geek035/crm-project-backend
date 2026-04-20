package fqw.crmprojectbackend.individual.domain.exception;

public class IndividualDuplicateEmailException extends RuntimeException {
    public IndividualDuplicateEmailException(String message) {
        super(message);
    }
}