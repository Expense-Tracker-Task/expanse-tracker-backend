package com.timurturbil.expansetrackerbackend.controller;

import com.timurturbil.expansetrackerbackend.dto.AuthResponse;
import com.timurturbil.expansetrackerbackend.dto.Response;
import com.timurturbil.expansetrackerbackend.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    @PostMapping("/register")
    public Response<AuthResponse> register(
            @RequestBody AuthResponse request
    ) {
        return authService.register(request);
    }
    @PostMapping("/login")
    public Response<AuthResponse> login(
            @RequestBody AuthResponse request
    ) {
        return authService.login(request);
    }
}
