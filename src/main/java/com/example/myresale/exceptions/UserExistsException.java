package com.example.myresale.exceptions;

import lombok.Data;
import lombok.Getter;

@Getter
public class UserExistsException extends RuntimeException {
    private final String message;

    public UserExistsException(){message = "User already exists!";}
    public UserExistsException(String message) {
        this.message = message;
    }
}
