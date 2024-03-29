package com.example.timesheet.service;

import com.example.timesheet.dto.UserRegistrationDto;
import com.example.timesheet.dto.UserResponseDto;
import com.example.timesheet.dto.UserUpdateDto;
import com.example.timesheet.model.User;
import com.example.timesheet.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

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

    public UserResponseDto registerNewUser(UserRegistrationDto registrationDto) {
        // Create a new User instance and set its properties based on the provided details
        User newUser = new User();
        newUser.setUsername(registrationDto.getUsername());
        newUser.setEmail(registrationDto.getEmail());
        // It's important to encode the password before saving it to the database
        newUser.setPasswordHash(passwordEncoder.encode(registrationDto.getPassword()));
        newUser.setRole(registrationDto.getRole());

        // Save the new User to the database
        User savedUser = userRepository.save(newUser);

        UserResponseDto responseDto = new UserResponseDto();
            responseDto.setId(savedUser.getId());
            responseDto.setUsername(savedUser.getUsername());
            responseDto.setEmail(savedUser.getEmail());
            responseDto.setRole(savedUser.getRole());
        
        // Return the saved User object
        return responseDto;
    }

    public List<UserResponseDto> findAllUsers() {
        return userRepository.findAll().stream()
                             .map(this::convertToDto) 
                             .collect(Collectors.toList());
    }
    
    public UserResponseDto convertToDto(User user) {
        UserResponseDto dto = new UserResponseDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole()); 
        return dto;
    }

    public UserResponseDto findUserById(Long userId) {
        User existingUser = userRepository.findById(userId)
            .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));

            return convertToDto(existingUser);
    }

    public UserResponseDto updateExistingUser(Long userId, UserUpdateDto userDto) {
    // Check if user exists
    User existingUser = userRepository.findById(userId)
            .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));

    // Update user details
    existingUser.setUsername(userDto.getUsername());
    existingUser.setRole(userDto.getRole());

    // Save updated user
    User savedUser = userRepository.save(existingUser);
    return convertToDto(savedUser);
}

}

