package fqw.crmprojectbackend.individual.application.exception;

public class IndividualDuplicateEmailException extends RuntimeException {
    public IndividualDuplicateEmailException(String message) {
        super(message);
    }
}