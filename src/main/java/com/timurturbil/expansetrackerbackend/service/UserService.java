package com.timurturbil.expansetrackerbackend.service;

import com.timurturbil.expansetrackerbackend.dto.UserDto;
import com.timurturbil.expansetrackerbackend.entity.User;
import com.timurturbil.expansetrackerbackend.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserRepository repository;
    public UserService(UserRepository repository) {
        this.repository = repository;
    }
    public UserDto findUserById(int id){
        User userData = repository.findById(id);
        return new UserDto(
                userData.getId(),
                userData.getUsername(),
                userData.getPassword(),
                userData.getEmail(),
                userData.getFirstName() + " " + userData.getLastName()
        );
    }
    public UserDto saveUser(UserDto userDto){
        User user = new User(
                userDto.getUsername(),
                userDto.getPassword(),
                userDto.getEmail(),
                userDto.getFullName().split(" ")[0],
                userDto.getFullName().split(" ")[1]
        );
        user = repository.save(user);
        userDto.setId(user.getId());
        return userDto;
    }
    public UserDto updateUser(int id, UserDto userDto){
        User user = repository.findById(id);
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFullName().split(" ")[0]);
        user.setLastName(userDto.getFullName().split(" ")[1]);
        user = repository.save(user);
        userDto.setId(user.getId());
        return userDto;
    }
    public void deleteUser(int id){
        repository.deleteById(id);
    }
    public List<UserDto> getAllUsers(){
        Iterable<User> iterableUsers = repository.findAll();
        List<UserDto> userDtos = new ArrayList<>();
        for(User user : iterableUsers){
            userDtos.add(new UserDto(
                    user.getId(),
                    user.getUsername(),
                    user.getPassword(),
                    user.getEmail(),
                    user.getFirstName() + " " + user.getLastName()
            ));
        }
        return userDtos;
    }
}
