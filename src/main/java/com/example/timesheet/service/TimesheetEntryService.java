package com.example.timesheet.service;

import com.example.timesheet.dto.TimesheetEntryDto;
import com.example.timesheet.dto.TimesheetResponseDto;
import com.example.timesheet.model.Program;
import com.example.timesheet.model.TimesheetEntry;
import com.example.timesheet.model.User;
import com.example.timesheet.repository.ProgramRepository;
import com.example.timesheet.repository.TimesheetEntryRepository;
import com.example.timesheet.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TimesheetEntryService {

    private final TimesheetEntryRepository timesheetEntryRepository;
    private final UserRepository userRepository;
    private final ProgramRepository programRepository;

    public TimesheetEntryService(TimesheetEntryRepository timesheetEntryRepository, UserRepository userRepository, ProgramRepository programRepository) {
        this.timesheetEntryRepository = timesheetEntryRepository;
        this.programRepository = programRepository;
        this.userRepository = userRepository;
    }

    public TimesheetResponseDto convertToDto(TimesheetEntry timesheetEntry) {
        TimesheetResponseDto dto = new TimesheetResponseDto();
        dto.setDate(timesheetEntry.getDate());
        dto.setDescription(timesheetEntry.getDescription());
        dto.setHours(timesheetEntry.getHours());
        dto.setProgram(timesheetEntry.getProgram().getProgramId());
        dto.setUser(timesheetEntry.getUser().getId());
        dto.setEntryId(timesheetEntry.getEntryId());
        return dto;
    }

    public List<TimesheetResponseDto> getAllTimesheetEntries() {
        return timesheetEntryRepository.findAll().stream()
                             .map(this::convertToDto) 
                             .collect(Collectors.toList());
    }

    public TimesheetEntry getTimesheetEntryById(Long id) {
        TimesheetEntry entry = timesheetEntryRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Timesheet entry not found for id :: " + id));

        return entry;
    }

    public List<TimesheetResponseDto> getTimesheetEntryByUserId(Long userId) {
        User user = userRepository.findById(userId)
        .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        return timesheetEntryRepository.findByUser(user).stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public TimesheetResponseDto createTimesheetEntry(TimesheetEntryDto dto) {
        User user = userRepository.findById(dto.getUser())
        .orElseThrow(() -> new RuntimeException("User not found with id: " + dto.getUser()));
        Program program = programRepository.findById(dto.getProgram())
            .orElseThrow(() -> new RuntimeException("Program not found with id: " + dto.getProgram()));
        
        TimesheetEntry entry = new TimesheetEntry();
        entry.setUser(user);
        entry.setProgram(program);
        entry.setDate(dto.getDate());
        entry.setHours(dto.getHours());
        entry.setDescription(dto.getDescription());
        
        TimesheetEntry savedEntry = timesheetEntryRepository.save(entry);

        return convertToDto(savedEntry);
    }

    public TimesheetResponseDto updateTimesheetEntry(Long entryId, TimesheetEntryDto dto) {
        TimesheetEntry existingEntry = getTimesheetEntryById(entryId);
        Program program = programRepository.findById(dto.getProgram())
            .orElseThrow(() -> new RuntimeException("Program not found with id: " + dto.getProgram()));

        // Update fields from timesheetEntryDetails to existingEntry
        existingEntry.setDate(dto.getDate());
        existingEntry.setHours(dto.getHours());
        existingEntry.setDescription(dto.getDescription());
        existingEntry.setProgram(program);

        TimesheetEntry savedEntry = timesheetEntryRepository.save(existingEntry);
        return convertToDto(savedEntry);
    }

    public void deleteTimesheetEntry(Long id) {
        TimesheetEntry timesheetEntry = getTimesheetEntryById(id);
        timesheetEntryRepository.delete(timesheetEntry);
    }
}
