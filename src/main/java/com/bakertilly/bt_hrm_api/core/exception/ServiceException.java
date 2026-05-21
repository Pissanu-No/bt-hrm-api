package com.bakertilly.bt_hrm_api.core.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ServiceException extends RuntimeException{
    private final HttpStatus statusCode;

    private final String errorCode;

    public ServiceException(HttpStatus statusCode, String errorCode, Throwable cause, String message, Object... args){
        super(String.format(message, args), cause);
        this.statusCode = statusCode;
        this.errorCode = errorCode;
    }
}