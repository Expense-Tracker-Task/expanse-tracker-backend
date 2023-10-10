package com.timurturbil.expansetrackerbackend.service;

import com.timurturbil.expansetrackerbackend.Constants;
import com.timurturbil.expansetrackerbackend.dto.Response;
import com.timurturbil.expansetrackerbackend.dto.UserDto;
import com.timurturbil.expansetrackerbackend.entity.User;
import com.timurturbil.expansetrackerbackend.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserRepository repository;

    private final ModelMapper modelMapper;

    public UserService(UserRepository repository, ModelMapper modelMapper) {
        this.repository = repository; this.modelMapper = modelMapper;
    }

    public Response<UserDto> findUserById(int id){
        try {
            User user = repository.findById(id);
            UserDto useDto = modelMapper.map(user, UserDto.class);
            return new Response<>(Constants.SUCCESS, Constants.USER_FOUND, useDto);
        } catch (Exception e) {
            return new Response<>(Constants.ERROR, e.getMessage(), null);
        }
    }
    public Response<UserDto> saveUser(UserDto userDto){
        try {
            User user = modelMapper.map(userDto, User.class);
            user = repository.save(user);
            userDto.setId(user.getId());
            return new Response<>(Constants.SUCCESS, Constants.USER_SAVED, userDto);
        } catch (Exception e) {
            return new Response<>(Constants.ERROR, e.getMessage(), null);
        }
    }

    public Response<UserDto> updateUser(UserDto userDto){
        try {
            //GET USER FROM DB
            User user = repository.findById((long) userDto.getId());

            //UPDATE USER
            user.setUsername(userDto.getUsername());
            user.setPassword(userDto.getPassword());
            user.setEmail(userDto.getEmail());
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());

            //SAVE USER
            user = repository.save(user);
            userDto.setId(user.getId());
            return new Response<>(Constants.SUCCESS, Constants.USER_UPDATED, userDto);
        } catch (Exception e) {
            return new Response<>(Constants.ERROR, e.getMessage(), null);
        }
    }

    public Response<String> deleteUser(int id){
        try {
            repository.deleteById((long) id);
            return new Response<>(Constants.SUCCESS, Constants.USER_DELETED, null);
        } catch (Exception e) {
            return new Response<>(Constants.ERROR, e.getMessage(), null);
        }
    }

    public Response<List<UserDto>> getAllUsers(){
        try {
            Iterable<User> iterableUsers = repository.findAll();
            List<UserDto> userDtoList = new ArrayList<>();
            for(User user : iterableUsers){
                userDtoList.add(modelMapper.map(user, UserDto.class));
            }
            return new Response<>(Constants.SUCCESS, Constants.USERS_FOUND, userDtoList);
        } catch (Exception e) {
            return new Response<>(Constants.ERROR, e.getMessage(), null);
        }
    }
}
