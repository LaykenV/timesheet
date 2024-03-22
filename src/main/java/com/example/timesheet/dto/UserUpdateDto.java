package com.example.timesheet.dto;

import com.example.timesheet.model.Role;

public class UserUpdateDto {
    private String username;
    private Role role; 

    // Getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
