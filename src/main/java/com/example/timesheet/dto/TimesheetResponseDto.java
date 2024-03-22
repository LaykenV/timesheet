package com.example.timesheet.dto;

import java.time.LocalDateTime;

public class TimesheetResponseDto {
    private Long entryId;
    private Long user;
    private Long program;
    private LocalDateTime date;
    private int hours;
    private String description;

    // Getters
    public Long getUser() {
        return user;
    }

    public Long getProgram() {
        return program;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public int getHours() {
        return hours;
    }

    public String getDescription() {
        return description;
    }

    public Long getEntryId() {
        return entryId;
    }

    // Setters
    public void setUser(Long user) {
        this.user = user;
    }

    public void setEntryId(Long entryId) {
        this.entryId = entryId;
    }

    public void setProgram(Long program) {
        this.program = program;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
