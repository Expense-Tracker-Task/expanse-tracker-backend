package com.timurturbil.expansetrackerbackend.controller;

import com.timurturbil.expansetrackerbackend.Constants;
import com.timurturbil.expansetrackerbackend.dto.AuthResponse;
import com.timurturbil.expansetrackerbackend.dto.GenericResponse;
import com.timurturbil.expansetrackerbackend.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public GenericResponse<Map<String, String>> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new GenericResponse<>(Constants.ERROR, Constants.VALIDATION_FAILED, errors);
    }

    @PostMapping("/register")
    public GenericResponse<AuthResponse> register(
            @Valid @RequestBody AuthResponse request
    ) {
        return authService.register(request);
    }
    @PostMapping("/login")
    public GenericResponse<AuthResponse> login(
            @Valid  @RequestBody AuthResponse request
    ) {
        return authService.login(request);
    }
}
