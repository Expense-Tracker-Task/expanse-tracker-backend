package com.timurturbil.expansetrackerbackend.controller;

import com.timurturbil.expansetrackerbackend.Constants;
import com.timurturbil.expansetrackerbackend.dto.GenericResponse;
import com.timurturbil.expansetrackerbackend.dto.UserDto;
import com.timurturbil.expansetrackerbackend.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping(path = "/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

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

    @GetMapping(path = "/{id}", produces = "application/json")
    public GenericResponse<UserDto> findUserById(@PathVariable int id) {
        return userService.findUserById(id);
    }

    @PostMapping(path = "", consumes = "application/json", produces = "application/json")
    public GenericResponse<UserDto> saveUser(@Valid @RequestBody UserDto userDto) {
        return userService.saveUser(userDto);
    }

    @PutMapping(path = "", consumes = "application/json", produces = "application/json")
    public GenericResponse<UserDto> updateUser(@Valid @RequestBody UserDto userDto) {
        return userService.updateUser(userDto);
    }

    @DeleteMapping(path = "/{id}")
    public GenericResponse<String> deleteUser(@PathVariable int id) {
        return userService.deleteUser(id);
    }

    @GetMapping(path = "/all", produces = "application/json")
    public GenericResponse<List<UserDto>> getAllUsers(){
        return userService.getAllUsers();
    }
}
