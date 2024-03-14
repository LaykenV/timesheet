package com.example.timesheet.service;

import com.example.timesheet.dto.UserRegistrationDto;
import com.example.timesheet.model.User;
import com.example.timesheet.repository.UserRepository;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerNewUser(UserRegistrationDto registrationDto) {
        // Create a new User instance and set its properties based on the provided details
        User newUser = new User();
        newUser.setUsername(registrationDto.getUsername());
        newUser.setEmail(registrationDto.getEmail());
        // It's important to encode the password before saving it to the database
        newUser.setPasswordHash(passwordEncoder.encode(registrationDto.getPassword()));
        newUser.setRole(registrationDto.getRole());
        
        // Assuming your User entity has fields for created_at and updated_at, you might want to set them here as well
        // Example:
         newUser.setCreatedAt(LocalDateTime.now());
         newUser.setUpdatedAt(LocalDateTime.now());

        // Save the new User to the database
        User savedUser = userRepository.save(newUser);
        
        // Return the saved User object
        return savedUser;
    }
    
    // Other service methods
}

