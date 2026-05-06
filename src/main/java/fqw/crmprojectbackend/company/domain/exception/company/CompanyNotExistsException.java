package fqw.crmprojectbackend.company.domain.exception.company;

public class CompanyNotExistsException extends RuntimeException {
    public CompanyNotExistsException(String message) {
        super(message);
    }
}
