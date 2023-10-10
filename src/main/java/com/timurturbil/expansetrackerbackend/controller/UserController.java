package com.timurturbil.expansetrackerbackend.controller;

import com.timurturbil.expansetrackerbackend.dto.Response;
import com.timurturbil.expansetrackerbackend.dto.UserDto;
import com.timurturbil.expansetrackerbackend.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public Response<UserDto> findUserById(@PathVariable int id) {
        return userService.findUserById(id);
    }

    @PostMapping(path = "", consumes = "application/json", produces = "application/json")
    public Response<UserDto> saveUser(@RequestBody UserDto userDto) {
        return userService.saveUser(userDto);
    }

    @PutMapping(path = "", consumes = "application/json", produces = "application/json")
    public Response<UserDto> updateUser(@RequestBody UserDto userDto) {
        return userService.updateUser(userDto);
    }

    @DeleteMapping(path = "/{id}")
    public Response<String> deleteUser(@PathVariable int id) {
        return userService.deleteUser(id);
    }

    @GetMapping(path = "/all", produces = "application/json")
    public Response<List<UserDto>> getAllUsers(){
        return userService.getAllUsers();
    }
}
