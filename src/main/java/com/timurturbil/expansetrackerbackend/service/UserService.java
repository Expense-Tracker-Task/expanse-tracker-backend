package com.timurturbil.expansetrackerbackend.service;

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

    public UserDto findUserById(int id){
        try {
            User user = repository.findById(id);
            return modelMapper.map(user, UserDto.class);
        } catch (Exception e) {
            return null;
        }
    }
    public UserDto saveUser(UserDto userDto){
        try {
            User user = modelMapper.map(userDto, User.class);
            user = repository.save(user);
            userDto.setId(user.getId());
            return userDto;
        } catch (Exception e) {
            return null;
        }
    }

    public UserDto updateUser(UserDto userDto){
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
            return userDto;
        } catch (Exception e) {
            return null;
        }
    }

    public void deleteUser(int id){
        repository.deleteById(id);
    }
    public List<UserDto> getAllUsers(){
        try {
            Iterable<User> iterableUsers = repository.findAll();
            List<UserDto> userDtos = new ArrayList<>();
            for(User user : iterableUsers){
                userDtos.add(modelMapper.map(user, UserDto.class));
            }
            return userDtos;
        } catch (Exception e) {
            return null;
        }
    }
}
