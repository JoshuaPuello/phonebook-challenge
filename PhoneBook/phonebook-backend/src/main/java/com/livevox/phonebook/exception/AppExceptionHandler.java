package com.livevox.phonebook.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(value = {ContactServiceException.class})
    public ResponseEntity<Object> handleContactServiceException(ContactServiceException ex, WebRequest request) {
        return new ResponseEntity<>(getErrorsMap(ex), new HttpHeaders(), ex.getHttpStatus());
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleOtherException(Exception ex, WebRequest request) {
        return new ResponseEntity<>(getErrorsMap(ex), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private Map<String, Object> getErrorsMap(Exception ex) {
        Map<String, Object> errors = new LinkedHashMap<>();
        errors.put("timestamp", String.valueOf(new Date()));
        errors.put("error", ex.getMessage());
        return errors;
    }
}
