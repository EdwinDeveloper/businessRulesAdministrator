package com.rule.admin.Config;

import com.rule.admin.Exception.RAException;
import com.rule.admin.model.response.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RAException.class)
    public ResponseEntity<ErrorResponse> handleRAException(RAException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                ex.getStatusCode().value(),
                ex.getCode(),
                ex.getMessage()
        );
        return new ResponseEntity<>(errorResponse, ex.getStatusCode());
    }

}
