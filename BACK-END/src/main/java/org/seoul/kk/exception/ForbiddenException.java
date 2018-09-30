package org.seoul.kk.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ForbiddenException extends BaseException {

    public ForbiddenException() {
        this(HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN.getReasonPhrase());
    }

    public ForbiddenException(String msg) {
        this(HttpStatus.FORBIDDEN.value(), msg);
    }

    public ForbiddenException(int code, String msg) {
        super(ErrorModel.builder()
                .code(code)
                .msg(msg)
                .timestamp(LocalDateTime.now())
                .build());
    }
}
