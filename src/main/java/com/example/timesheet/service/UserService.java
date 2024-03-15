package com.example.timesheet.service;

import com.example.timesheet.dto.UserRegistrationDto;
import com.example.timesheet.model.User;
import com.example.timesheet.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

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

        // Save the new User to the database
        User savedUser = userRepository.save(newUser);
        
        // Return the saved User object
        return savedUser;
    }
    
    // Other service methods
}

