package com.example.myresale.controllers.advices;

import com.example.myresale.exceptions.ItemNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;



import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {
    Logger logger = Logger.getLogger(GlobalControllerExceptionHandler.class.getName());

    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<String> itemNotFoundExceptionHandler(ItemNotFoundException exception){
        logger.info(exception.getMessage());
        return ResponseEntity
                .badRequest()
                .contentType(MediaType.APPLICATION_JSON)
                .body("Can't find item with id: " + exception.getId());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<String>> validationErrorHandler(MethodArgumentNotValidException ex){
        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> errors.add(error.getDefaultMessage()));

        logger.info(ex.getMessage());
        return ResponseEntity
                .badRequest()
                .contentType(MediaType.APPLICATION_JSON)
                .body(errors);
    }

    @ExceptionHandler(HttpMessageConversionException.class)
    public ResponseEntity<String> HttpBodyConversionExceptionHandler(HttpMessageConversionException ex){
        logger.info(ex.getMessage());
        return ResponseEntity
                .badRequest()
                .contentType(MediaType.APPLICATION_JSON)
                .body(ex.getMessage() + "with cause: " + ex.getCause());
    }

}
