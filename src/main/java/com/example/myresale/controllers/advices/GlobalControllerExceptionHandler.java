package com.example.myresale.controllers.advices;

import com.example.myresale.exceptions.ItemNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<?> itemNotFoundExceptionHandler(ItemNotFoundException exception){
        return ResponseEntity
                .badRequest()
                .body("Can't find item with id: " + exception.getId());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> validationErrorHandler(MethodArgumentNotValidException ex){
        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> errors.add(error.getDefaultMessage()));

        return ResponseEntity
                .badRequest()
                .body(errors);
    }

    @ExceptionHandler(HttpMessageConversionException.class)
    public ResponseEntity<?> HttpBodyConversionExceptionHandler(HttpMessageConversionException ex){
        return ResponseEntity
                .badRequest()
                .body(ex.getMessage());
    }

}
