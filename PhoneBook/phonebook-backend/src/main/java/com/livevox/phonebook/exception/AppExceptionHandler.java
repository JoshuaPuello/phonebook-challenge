package com.livevox.phonebook.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(value = {ContactServiceException.class})
    public ResponseEntity<Object> handleContactServiceException(ContactServiceException ex) {
        return new ResponseEntity<>(getErrorsMap(ex), new HttpHeaders(), ex.getHttpStatus());
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleOtherException(Exception ex) {
        return new ResponseEntity<>(getErrorsMap(ex), new HttpHeaders(), INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, Object> errors = getErrorsMap(ex);
        String strErrors = ex.getBindingResult().getAllErrors()
                .stream()
                .map(err -> ((FieldError) err).getField())
                .collect(Collectors.joining(", ", "Invalid status for fields: [", "]"));
        if (strErrors.length() > 0) errors.put("error", strErrors);
        return new ResponseEntity<>(errors, new HttpHeaders(), BAD_REQUEST);
    }

    private Map<String, Object> getErrorsMap(Exception ex) {
        Map<String, Object> errors = new LinkedHashMap<>();
        errors.put("timestamp", String.valueOf(new Date()));
        errors.put("error", ex.getMessage());
        return errors;
    }
}
