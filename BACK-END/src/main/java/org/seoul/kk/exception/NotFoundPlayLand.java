package org.seoul.kk.exception;

import java.time.LocalDateTime;

public class NotFoundPlayLand extends BaseException {

    public NotFoundPlayLand() {
        this("Not found playLand");
    }

    public NotFoundPlayLand(String msg) {
        this(4004, msg);
    }

    public NotFoundPlayLand(int code, String msg) {
        super(ErrorModel.builder()
                .code(code)
                .msg(msg)
                .timestamp(LocalDateTime.now())
                .build());
    }
}