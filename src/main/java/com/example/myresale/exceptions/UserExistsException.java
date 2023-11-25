package com.example.myresale.exceptions;

import lombok.Data;
import lombok.Getter;

@Getter
public class UserExistsException extends RuntimeException {
    private final String message;

    protected UserExistsException(String message) {
        this.message = message;
    }
}
