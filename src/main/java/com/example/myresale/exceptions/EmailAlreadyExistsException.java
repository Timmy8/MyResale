package com.example.myresale.exceptions;

public class EmailAlreadyExistsException extends UserExistsException{

    public EmailAlreadyExistsException() {
        super("Email already exists!");
    }
}
