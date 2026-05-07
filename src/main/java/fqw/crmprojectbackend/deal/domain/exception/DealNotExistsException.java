package fqw.crmprojectbackend.deal.domain.exception;

public class DealNotExistsException extends RuntimeException {
    public DealNotExistsException(String message) {
        super(message);
    }
}
