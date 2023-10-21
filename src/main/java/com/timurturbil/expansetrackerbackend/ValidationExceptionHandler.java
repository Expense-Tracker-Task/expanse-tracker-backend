package com.timurturbil.expansetrackerbackend;

import com.timurturbil.expansetrackerbackend.dto.GenericResponse;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

//@ControllerAdvice This annotation is used to handle exceptions globally for all controllers
//@RestControllerAdvice This annotation is used to handle exceptions globally for all controllers and also to send response back to the client
@RestControllerAdvice
public class ValidationExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public GenericResponse<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new GenericResponse<>(Constants.ERROR, Constants.VALIDATION_FAILED, errors);
    }
}

