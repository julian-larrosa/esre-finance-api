package com.esre.movement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MovementNotFoundException extends RuntimeException {
    public MovementNotFoundException(String message) {
        super(message);
    }
}
