package org.seoul.kk.exception;

class BaseException extends RuntimeException {

    protected ErrorModel error;

    protected BaseException(ErrorModel error) {
        super(error.getMsg(), null);
        this.error = error;
    }

}
