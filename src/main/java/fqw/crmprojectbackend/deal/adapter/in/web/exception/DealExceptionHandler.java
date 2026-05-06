package fqw.crmprojectbackend.deal.adapter.in.web.exception;

import fqw.crmprojectbackend.common.web.exception.HTTPErrorCode;
import fqw.crmprojectbackend.common.web.exception.WebError;
import fqw.crmprojectbackend.deal.adapter.in.web.DealController;
import fqw.crmprojectbackend.deal.domain.exception.*;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice(basePackageClasses = DealController.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class DealExceptionHandler {
    @ExceptionHandler(DealDuplicateNumberException.class)
    public ResponseEntity<WebError> handleDealDuplicateNumberException(
            DealDuplicateNumberException exception) {
        var error = new WebError(
                HTTPErrorCode.CONFLICT.getStatus().value(),
                HTTPErrorCode.CONFLICT.getTitle(),
                List.of(exception.getMessage())
        );

        return ResponseEntity.status(HTTPErrorCode.CONFLICT.getStatus()).body(error);
    }

    @ExceptionHandler(DealClientNotFoundException.class)
    public ResponseEntity<WebError> handleDealClientNotFoundException(
            DealClientNotFoundException exception) {
        var error = new WebError(
                HTTPErrorCode.RESOURCE_NOT_FOUND.getStatus().value(),
                HTTPErrorCode.RESOURCE_NOT_FOUND.getTitle(),
                List.of(exception.getMessage())
        );

        return ResponseEntity.status(HTTPErrorCode.RESOURCE_NOT_FOUND.getStatus()).body(error);
    }

    @ExceptionHandler({
            DealIllegalAmountException.class,
            DealIllegalClientException.class,
            DealIllegalCurrencyException.class,
            DealIllegalLossReasonException.class,
            DealIllegalPriorityException.class,
            DealIllegalProductException.class,
            DealIllegalSourceException.class,
            DealIllegalStageException.class,
            IllegalArgumentException.class
    })
    public ResponseEntity<WebError> handleDealInvalidDataException(
            Exception exception) {
        var error = new WebError(
                HTTPErrorCode.VALIDATION_ERROR.getStatus().value(),
                HTTPErrorCode.VALIDATION_ERROR.getTitle(),
                List.of(exception.getMessage())
        );

        return ResponseEntity.status(HTTPErrorCode.VALIDATION_ERROR.getStatus()).body(error);
    }

    @ExceptionHandler({
            DealClosedException.class,
            DealIllegalStageChangeException.class
    })
    public ResponseEntity<WebError> handleDealBusinessLogicException(
            Exception exception) {
        var error = new WebError(
                HTTPErrorCode.UNPROCESSABLE_CONTENT.getStatus().value(),
                HTTPErrorCode.UNPROCESSABLE_CONTENT.getTitle(),
                List.of(exception.getMessage())
        );

        return ResponseEntity.status(HTTPErrorCode.UNPROCESSABLE_CONTENT.getStatus()).body(error);
    }

}
