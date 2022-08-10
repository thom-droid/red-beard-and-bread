package com.firsttoy.redbeardandbread.exception;

import lombok.Getter;

@Getter
public class CustomRuntimeException extends RuntimeException {

    private Exceptions exceptions;
    private int code;

    public CustomRuntimeException(Exceptions exceptions) {
        super(exceptions.getDescription());
        this.code = exceptions.getCode();
        this.exceptions = exceptions;
    }
}
