package com.example.myresale.exceptions;

public class UsernameExistsException extends UserExistsException{
    public UsernameExistsException(){
        super("Username already exists");
    }
}
