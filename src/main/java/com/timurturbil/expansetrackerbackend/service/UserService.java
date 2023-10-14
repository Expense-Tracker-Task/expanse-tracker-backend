package com.timurturbil.expansetrackerbackend.service;

import com.timurturbil.expansetrackerbackend.Constants;
import com.timurturbil.expansetrackerbackend.dto.GenericResponse;
import com.timurturbil.expansetrackerbackend.dto.UserDto;
import com.timurturbil.expansetrackerbackend.entity.User;
import com.timurturbil.expansetrackerbackend.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository repository;

    private final ModelMapper modelMapper;

    public UserService(UserRepository repository, ModelMapper modelMapper) {
        this.repository = repository; this.modelMapper = modelMapper;
    }

    public GenericResponse<UserDto> findUserById(int id){
        try {
            User user = repository.findById(id);
            UserDto useDto = modelMapper.map(user, UserDto.class);
            return new GenericResponse<>(Constants.SUCCESS, Constants.USER_FOUND, useDto);
        } catch (Exception e) {
            return new GenericResponse<>(Constants.ERROR, e.getMessage(), null);
        }
    }
    public GenericResponse<UserDto> saveUser(UserDto userDto){
        try {
            User user = modelMapper.map(userDto, User.class);
            user = repository.save(user);
            userDto.setId(user.getId());
            return new GenericResponse<>(Constants.SUCCESS, Constants.USER_SAVED, userDto);
        } catch (Exception e) {
            return new GenericResponse<>(Constants.ERROR, e.getMessage(), null);
        }
    }

    public GenericResponse<UserDto> updateUser(UserDto userDto){
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
            return new GenericResponse<>(Constants.SUCCESS, Constants.USER_UPDATED, userDto);
        } catch (Exception e) {
            return new GenericResponse<>(Constants.ERROR, e.getMessage(), null);
        }
    }

    public GenericResponse<String> deleteUser(int id){
        try {
            repository.deleteById(id);
            return new GenericResponse<>(Constants.SUCCESS, Constants.USER_DELETED, null);
        } catch (Exception e) {
            return new GenericResponse<>(Constants.ERROR, e.getMessage(), null);
        }
    }

    public GenericResponse<List<UserDto>> getAllUsers(){
        try {
            Iterable<User> iterableUsers = repository.findAll();
            List<UserDto> userDtoList = new ArrayList<>();
            for(User user : iterableUsers){
                userDtoList.add(modelMapper.map(user, UserDto.class));
            }
            return new GenericResponse<>(Constants.SUCCESS, Constants.USERS_FOUND, userDtoList);
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
