package com.timurturbil.expansetrackerbackend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/user")
public class UserController {
    @GetMapping(path = "/info", produces = "application/json")
    public String getUserInfo() {
        return "some user info";
    }
}
