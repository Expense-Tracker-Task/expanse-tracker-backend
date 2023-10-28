package com.timurturbil.expansetrackerbackend.service;

import com.timurturbil.expansetrackerbackend.utils.Constants;
import com.timurturbil.expansetrackerbackend.dto.AuthResponse;
import com.timurturbil.expansetrackerbackend.dto.GenericResponse;
import com.timurturbil.expansetrackerbackend.entity.User;
import com.timurturbil.expansetrackerbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public GenericResponse<AuthResponse> register(AuthResponse authResponse) {
        try {
            //CREATE USER OBJECT AND SET PROPERTIES FROM AUTH RESPONSE
            var user = new User();
            user.setUsername(authResponse.getUsername());
            user.setPassword(passwordEncoder.encode(authResponse.getPassword()));
            user.setEmail(authResponse.getEmail());
            user.setFirstName(authResponse.getFirstName());
            user.setLastName(authResponse.getLastName());

            //SAVE USER
            userRepository.save(user);

            //GENERATE TOKEN AND SET IT TO AUTH RESPONSE
            var jwtToken = jwtService.generateToken(user).getData();
            authResponse.setAccessToken(jwtToken);

            //SET BALANCE VALUE TO AUTH RESPONSE
            authResponse.setBalance(BigDecimal.valueOf(0));

            //RETURN RESPONSE
            return new GenericResponse<>(Constants.SUCCESS, Constants.USER_REGISTERED, authResponse);
        } catch (Exception e) {
            return new GenericResponse<>(Constants.ERROR, e.getMessage(), null);
        }
    }

    //TODO: refresh token to get new access token
    //TODO: revoke token to logout user
    public GenericResponse<AuthResponse> login(AuthResponse authResponse) {
        try {
            //SET USERNAME AND PASSWORD
            String username = authResponse.getUsername();
            String password = authResponse.getPassword();

            //GET USER FROM DATABASE
            User user = userRepository.findByUsername(username);
            if(user == null) return new GenericResponse<>(Constants.ERROR, Constants.USER_NOT_FOUND, null);

            //AUTHENTICATE USER
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            username,
                            password
                    )
            );

            //GENERATE TOKEN
            String jwtToken = jwtService.generateToken(user).getData();

            //SET USER PROPERTIES AND TOKEN TO AUTH RESPONSE
            authResponse.setEmail(user.getEmail());
            authResponse.setFirstName(user.getFirstName());
            authResponse.setLastName(user.getLastName());
            authResponse.setAccessToken(jwtToken);
            authResponse.setBalance(user.getBalance());

            //RETURN RESPONSE
            return new GenericResponse<>(Constants.SUCCESS, Constants.USER_LOGGED_IN, authResponse);
        } catch (Exception e) {
            return new GenericResponse<>(Constants.ERROR, e.getMessage(), null);
        }
    }

}
