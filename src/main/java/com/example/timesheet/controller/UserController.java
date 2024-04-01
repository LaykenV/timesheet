package com.example.timesheet.controller;

import com.example.timesheet.dto.UserRegistrationDto;
import com.example.timesheet.dto.UserResponseDto;
import com.example.timesheet.dto.UserUpdateDto;
import com.example.timesheet.model.User; // Ensure you have a User model
import com.example.timesheet.service.UserService;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @CrossOrigin
    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> registerUser(@RequestBody UserRegistrationDto registrationDto) {
        UserResponseDto registeredUser = userService.registerNewUser(registrationDto);
        return ResponseEntity.ok(registeredUser);
    }

    @CrossOrigin
    @GetMapping("/all")
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        List<UserResponseDto> users = userService.findAllUsers();
        return ResponseEntity.ok(users);
    }

    @CrossOrigin
    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDto> findUser(@PathVariable Long userId) {
        UserResponseDto user = userService.findUserById(userId);
        return ResponseEntity.ok(user);
    }
    
    @CrossOrigin
    @PutMapping("/{userId}")
public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long userId, @RequestBody UserUpdateDto userDto) {
    UserResponseDto updatedUser = userService.updateExistingUser(userId, userDto);
    return ResponseEntity.ok(updatedUser);
}

    

}
