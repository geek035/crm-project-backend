package fqw.crmprojectbackend.deal.domain.exception;

public class DealClosedException extends RuntimeException {
    public DealClosedException(String message) {
        super(message);
    }
}
