package com.challenge.alura.challenge.infra.exception;

import org.springframework.http.HttpStatusCode;

public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }



}
