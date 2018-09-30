package org.seoul.kk.exception;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ErrorModel {

    private int code;
    private String msg;
    private LocalDateTime timestamp;

}
