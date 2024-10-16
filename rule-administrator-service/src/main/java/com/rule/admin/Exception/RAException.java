package com.rule.admin.Exception;

import org.springframework.http.HttpStatus;

public class RAException extends RuntimeException{

    private final HttpStatus statusCode;
    private final String code;


    public RAException(HttpStatus statusCode, String code, String message) {
        super(message);
        this.statusCode = statusCode;
        this.code = code;
    }

    public RAException(HttpStatus statusCode, String code, String message, Throwable cause) {
        super(message, cause);
        this.statusCode = statusCode;
        this.code = code;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public String getCode() {
        return code;
    }
}
