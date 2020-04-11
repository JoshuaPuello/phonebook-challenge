package com.livevox.phonebook.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ContactServiceException extends RuntimeException {

    private static final long serialVersionUID = 7670401749597801900L;
    private final HttpStatus httpStatus;

    public ContactServiceException(String message, HttpStatus status) {
        super(message);
        this.httpStatus = status;
    }
}
