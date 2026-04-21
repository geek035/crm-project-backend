package fqw.crmprojectbackend.common.web.exception;

import lombok.Getter;

public class WebException extends RuntimeException {
    @Getter
    private final HTTPErrorCode code;

    public WebException(HTTPErrorCode code, String message) {
        super(message);
        this.code = code;
    }
}
