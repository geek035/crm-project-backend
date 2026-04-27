package fqw.crmprojectbackend.company.domain.exception;

public class CompanyNotExistsException extends RuntimeException {
    public CompanyNotExistsException(String message) {
        super(message);
    }
}
