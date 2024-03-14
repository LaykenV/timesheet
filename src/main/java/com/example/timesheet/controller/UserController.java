package com.example.timesheet.controller;

import com.example.timesheet.dto.UserRegistrationDto;
import com.example.timesheet.model.User; // Ensure you have a User model
import com.example.timesheet.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody UserRegistrationDto registrationDto) {
        User registeredUser = userService.registerNewUser(registrationDto);
        return ResponseEntity.ok(registeredUser);
    }

}
