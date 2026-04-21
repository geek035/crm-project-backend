package fqw.crmprojectbackend.common.web.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
public class WebFallbackExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(WebFallbackExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<WebError> handleUnexpectedException(Exception exception) {
        var log = String.format("%s: %s", exception.getClass(), exception.getMessage());
        logger.error(log);

        var error = new WebError(
                HTTPErrorCode.INTERNAL_ERROR.getStatus().value(),
                HTTPErrorCode.INTERNAL_ERROR.getTitle(),
                List.of(exception.getMessage()));

        return ResponseEntity
                .status(HTTPErrorCode.INTERNAL_ERROR.getStatus())
                .body(error);
    }
}
