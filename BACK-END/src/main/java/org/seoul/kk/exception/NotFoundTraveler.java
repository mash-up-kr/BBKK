package org.seoul.kk.exception;

import java.time.LocalDateTime;

public class NotFoundTraveler extends BaseException {

    public NotFoundTraveler() {
        this("Not found traveler");
    }

    public NotFoundTraveler(String msg) {
        this(4004, msg);
    }

    public NotFoundTraveler(int code, String msg)  {
        super(ErrorModel.builder()
                .code(code)
                .msg(msg)
                .timestamp(LocalDateTime.now())
                .build());
    }

}
