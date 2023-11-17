package com.example.myresale.controllers.advices;

import com.example.myresale.exceptions.ItemNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@ControllerAdvice
public class GlobalControllerExceptionHandler {
    Logger logger = Logger.getLogger(GlobalControllerExceptionHandler.class.getName());

    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<String> itemNotFoundExceptionHandler(ItemNotFoundException exception) {
        logger.info(exception.getMessage());
        return ResponseEntity
                .badRequest()
                .contentType(MediaType.TEXT_PLAIN)
                .body("Can't find item with id: " + exception.getId());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String validationErrorHandler(MethodArgumentNotValidException ex, Model model, HttpServletRequest http) {
        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> errors.add(error.getDefaultMessage()));

        logger.info(ex.getMessage());
        model.addAttribute("errors", errors);

        System.out.println(http.getRequestURI());

        return http.getRequestURI();
    }

    @ExceptionHandler(HttpMessageConversionException.class)
    public ResponseEntity<String> HttpBodyConversionExceptionHandler(HttpMessageConversionException ex) {
        logger.info(ex.getMessage());
        return ResponseEntity
                .badRequest()
                .contentType(MediaType.TEXT_PLAIN)
                .body(ex.getMessage() + "with cause: " + ex.getCause());
    }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<String> NumberFormatException(NumberFormatException ex) {
        logger.info(ex.getMessage());
        return ResponseEntity
                .badRequest()
                .contentType(MediaType.TEXT_PLAIN)
                .body("Page not found!");
    }

}
