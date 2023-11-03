package com.example.myresale.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ItemNotFoundException extends RuntimeException{
    private long id;

}
