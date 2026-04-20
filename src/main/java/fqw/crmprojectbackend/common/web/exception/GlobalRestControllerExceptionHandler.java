package fqw.crmprojectbackend.common.web.exception;

import fqw.crmprojectbackend.common.query.criterion.filter.IllegalFilterMatchModeException;
import fqw.crmprojectbackend.common.query.UnknowQueryPropertyException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.data.core.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;

@RestControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
public class GlobalRestControllerExceptionHandler {

    private static final Log log = LogFactory.getLog(GlobalRestControllerExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<WebError> handleUnexpectedException(Exception exception) {
        log.error(exception);

        var error = new WebError(
                HTTPErrorCode.INTERNAL_ERROR.getStatus().value(),
                HTTPErrorCode.INTERNAL_ERROR.getTitle(),
                List.of(exception.getMessage()));

        return ResponseEntity
                .status(HTTPErrorCode.INTERNAL_ERROR.getStatus())
                .body(error);
    }

    @ExceptionHandler(GeneralAPIException.class)
    public ResponseEntity<WebError> handleGeneralAPIException(
            GeneralAPIException exception) {
        var error = new WebError(
                exception.getCode().getStatus().value(),
                exception.getCode().getTitle(),
                List.of(exception.getMessage()));

        return ResponseEntity.status(exception.getCode().getStatus()).body(error);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<WebError> handleMethodArgumentTypeMismatchException(
            MethodArgumentTypeMismatchException exception) {
        var error = new WebError(
                HttpStatus.BAD_REQUEST.value(),
                "Ошибка преобразования аргумента",
                List.of(exception.getMessage())
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<WebError> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exception) {
        var messages = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();

        var error = new WebError(
                HTTPErrorCode.VALIDATION_ERROR.getStatus().value(),
                HTTPErrorCode.VALIDATION_ERROR.getTitle(),
                messages);

        return ResponseEntity.status(HTTPErrorCode.VALIDATION_ERROR.getStatus()).body(error);
    }

    @ExceptionHandler({
            IllegalFilterMatchModeException.class,
            UnknowQueryPropertyException.class,
    })
    public ResponseEntity<WebError> handleFilterException(RuntimeException exception) {
        var error = new WebError(
                HTTPErrorCode.VALIDATION_ERROR.getStatus().value(),
                HTTPErrorCode.VALIDATION_ERROR.getTitle(),
                List.of(exception.getMessage()));

        return ResponseEntity.status(HTTPErrorCode.VALIDATION_ERROR.getStatus()).body(error);
    }

    @ExceptionHandler(PropertyReferenceException.class)
    public ResponseEntity<WebError> handlePropertyReferenceException(
            PropertyReferenceException exception) {
        var message = String.format(
                "Неизвестное поле '%s' запрашиваемой сущности",
                exception.getPropertyName());

        var error = new WebError(
                HTTPErrorCode.VALIDATION_ERROR.getStatus().value(),
                HTTPErrorCode.VALIDATION_ERROR.getTitle(),
                List.of(message));

        return ResponseEntity.status(HTTPErrorCode.VALIDATION_ERROR.getStatus()).body(error);
    }
}
