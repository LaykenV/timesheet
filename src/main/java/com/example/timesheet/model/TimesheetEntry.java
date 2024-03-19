package com.example.timesheet.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "timesheet_entries")
public class TimesheetEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long entryId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "program_id", nullable = false)
    private Program program;

    private LocalDateTime date;

    private Double hours;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false, updatable = false)
    @org.hibernate.annotations.CreationTimestamp
    private LocalDateTime createdAt;

    @Column(nullable = false)
    @org.hibernate.annotations.UpdateTimestamp
    private LocalDateTime updatedAt;

    public TimesheetEntry() {}

    // Getters
    public Long getEntryId() {
        return entryId;
    }

    public User getUser() {
        return user;
    }

    public Program getProgram() {
        return program;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public Double getHours() {
        return hours;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    // Setters
    public void setUser(User user) {
        this.user = user;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setHours(Double hours) {
        this.hours = hours;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
