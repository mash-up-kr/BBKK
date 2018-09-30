package org.seoul.kk.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = BaseException.class)
    public ResponseEntity<ErrorModel> handledExceptionHandler(BaseException exception) throws RuntimeException {
        ErrorModel error = exception.error;
        log.error("Rest api error : {}", error.getMsg());

        switch (error.getCode()) {
            case 400:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
            case 403:
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
            case 4004:
                return ResponseEntity.status(HttpStatus.OK).body(error);
            case 406:
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(error);
            default:
                throw new RuntimeException();
        }

    }

    //TODO exception.printStackStrace()를 통해 출력되는 에러 로그를 콘솔이 아닌 파일로 남겨야합니다.
    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ErrorModel> unhandledExceptionHandler(RuntimeException exception) {
        log.error("unhandledException occur : {}", exception.getMessage());
        exception.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorModel.builder()
                        .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .msg(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                        .timestamp(LocalDateTime.now())
                        .build());
    }

}
