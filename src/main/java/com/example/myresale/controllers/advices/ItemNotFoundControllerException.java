package com.example.myresale.controllers.advices;

import com.example.myresale.exceptions.ItemNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ItemNotFoundControllerException {

    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<?> itemNotFoundException(ItemNotFoundException exception){
        return ResponseEntity
                .badRequest()
                .body("Can't find item with id: " + exception.getId());
    }
}
