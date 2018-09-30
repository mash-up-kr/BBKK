package org.seoul.kk.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class BadRequestException extends BaseException {

    public BadRequestException() {
        this(HttpStatus.BAD_REQUEST.getReasonPhrase());
    }

    public BadRequestException(String msg) {
        this(HttpStatus.BAD_REQUEST.value(), msg);
    }

    public BadRequestException(int code, String msg) {
        super(ErrorModel.builder()
                .code(code)
                .msg(msg)
                .timestamp(LocalDateTime.now())
                .build());
    }

}
