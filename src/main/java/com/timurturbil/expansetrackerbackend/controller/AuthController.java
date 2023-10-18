package com.timurturbil.expansetrackerbackend.controller;

import com.timurturbil.expansetrackerbackend.dto.AuthResponse;
import com.timurturbil.expansetrackerbackend.dto.GenericResponse;
import com.timurturbil.expansetrackerbackend.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public GenericResponse<AuthResponse> register(
            @RequestBody AuthResponse request
    ) {
        return authService.register(request);
    }
    @PostMapping("/login")
    public GenericResponse<AuthResponse> login(
            @RequestBody AuthResponse request
    ) {
        return authService.login(request);
    }
}
