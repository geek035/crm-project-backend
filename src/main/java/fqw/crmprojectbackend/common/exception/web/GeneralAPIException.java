package fqw.crmprojectbackend.common.exception.web;

import lombok.Getter;

public class GeneralAPIException extends RuntimeException {
    @Getter
    private final APIErrorCode code;

    public GeneralAPIException(APIErrorCode code, String message) {
        super(message);
        this.code = code;
    }
}
