package com.timurturbil.expansetrackerbackend.controller;

import com.timurturbil.expansetrackerbackend.dto.GenericResponse;
import com.timurturbil.expansetrackerbackend.dto.UserDto;
import com.timurturbil.expansetrackerbackend.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(path = "/me", produces = "application/json")
    public GenericResponse<UserDto> findUserById(@RequestHeader("Authorization") String bearerToken) {
        return userService.findUserById(bearerToken);
    }

    @PutMapping(path = "/me", consumes = "application/json", produces = "application/json")
    public GenericResponse<UserDto> updateUser(@RequestHeader("Authorization") String bearerToken, @Valid @RequestBody UserDto userDto) {
        return userService.updateUser(bearerToken, userDto);
    }

    @PutMapping(path = "/me/password", consumes = "application/json", produces = "application/json")
    public GenericResponse<UserDto> updateUserPassword(@RequestHeader("Authorization") String bearerToken, @RequestBody UserDto userDto) {
        return userService.updateUserPassword(bearerToken, userDto);
    }
}
