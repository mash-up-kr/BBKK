package org.seoul.kk.exception;

import java.time.LocalDateTime;

public class NotAcceptableException extends BaseException {

    public NotAcceptableException() {
        this(406, "Not Acceptable");
    }

    public NotAcceptableException(String msg) {
        this(406, msg);
    }

    public NotAcceptableException(int code, String msg) {
        super(ErrorModel.builder()
                .code(code)
                .msg(msg)
                .timestamp(LocalDateTime.now())
                .build());
    }
}
