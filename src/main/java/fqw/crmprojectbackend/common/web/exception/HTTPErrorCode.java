package fqw.crmprojectbackend.common.web.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum HTTPErrorCode {
    VALIDATION_ERROR(HttpStatus.BAD_REQUEST, "Ошибка валидации данных"),
    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND, "Ресурс не найден"),
    CONFLICT(HttpStatus.CONFLICT, "Конфликт"),
    FORBIDDEN(HttpStatus.FORBIDDEN, "Запрещенный ресурс"),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "Ошибка авторизации"),
    INTERNAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Внутренняя ошибка сервера");

    private final HttpStatus status;
    private final String title;
}
