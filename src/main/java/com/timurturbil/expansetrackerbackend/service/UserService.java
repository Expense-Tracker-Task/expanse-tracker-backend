package com.timurturbil.expansetrackerbackend.service;

import com.timurturbil.expansetrackerbackend.utils.Constants;
import com.timurturbil.expansetrackerbackend.dto.GenericResponse;
import com.timurturbil.expansetrackerbackend.dto.UserDto;
import com.timurturbil.expansetrackerbackend.entity.User;
import com.timurturbil.expansetrackerbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository repository;

    private final ModelMapper modelMapper;

    private final JwtService jwtService;

    private final PasswordEncoder passwordEncoder;

    public GenericResponse<UserDto> findUserById(String bearerToken){
        try {
            int userId = jwtService.extractUserId(bearerToken);
            User user = repository.findById(userId);
            UserDto useDto = modelMapper.map(user, UserDto.class);
            return new GenericResponse<>(Constants.SUCCESS, Constants.USER_FOUND, useDto);
        } catch (Exception e) {
            return new GenericResponse<>(Constants.ERROR, e.getMessage(), null);
        }
    }

    public GenericResponse<UserDto> updateUser(String bearerToken, UserDto userDto){
        try {
            //EXTRACT USER ID FROM TOKEN
            int userId = jwtService.extractUserId(bearerToken);

            //GET USER FROM DB
            User user = repository.findById(userId);

            //UPDATE USER
            user.setUsername(userDto.getUsername());
            user.setPassword(user.getPassword());
            user.setEmail(userDto.getEmail());
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());

            //SAVE USER
            user = repository.save(user);
            userDto.setId(user.getId());

            return new GenericResponse<>(Constants.SUCCESS, Constants.USER_UPDATED, userDto);
        } catch (Exception e) {
            return new GenericResponse<>(Constants.ERROR, e.getMessage(), null);
        }
    }

    public GenericResponse<UserDto> updateUserPassword(String bearerToken, UserDto userDto){
        try {
            //EXTRACT USER ID FROM TOKEN
            int userId = jwtService.extractUserId(bearerToken);

            //GET USER FROM DB
            User user = repository.findById(userId);

            //UPDATE USER'S PASSWORD
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));

            //SAVE USER
            user = repository.save(user);
            userDto.setId(user.getId());

            return new GenericResponse<>(Constants.SUCCESS, Constants.USER_UPDATED, userDto);
        } catch (Exception e) {
            return new GenericResponse<>(Constants.ERROR, e.getMessage(), null);
        }
    }

    //TODO: entity user modeli ile userdetails'in user model isimleri aynı olduğu için bu şekilde uzattım.
    //TODO: Kendi user entity modelimin ismini farklılaştırabilirim düzeltmek için
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUsername(username);
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }
}
