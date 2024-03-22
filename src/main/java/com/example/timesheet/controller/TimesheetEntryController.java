package com.example.timesheet.controller;

import com.example.timesheet.dto.TimesheetEntryDto;
import com.example.timesheet.dto.TimesheetResponseDto;
import com.example.timesheet.model.TimesheetEntry;
import com.example.timesheet.model.User;
import com.example.timesheet.service.TimesheetEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.List;


@RestController
@RequestMapping("/api/timesheetEntries")
public class TimesheetEntryController {

    @Autowired
    private TimesheetEntryService timesheetEntryService;

    @GetMapping
    public ResponseEntity<List<TimesheetResponseDto>> getAllTimesheetEntries() {
        return ResponseEntity.ok(timesheetEntryService.getAllTimesheetEntries());
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<TimesheetResponseDto>> getTimesheetEntryByUserId(@PathVariable Long userId) {
        List<TimesheetResponseDto> entries = timesheetEntryService.getTimesheetEntryByUserId(userId);
        return ResponseEntity.ok(entries);
    }
    

    @PostMapping
    public ResponseEntity<TimesheetResponseDto> createTimesheetEntry(@RequestBody TimesheetEntryDto dto) {
        TimesheetResponseDto createdEntry = timesheetEntryService.createTimesheetEntry(dto);
        return ResponseEntity.ok(createdEntry);
    }

    @PutMapping("/{entryId}")
    public ResponseEntity<TimesheetResponseDto> updateTimesheetEntry(@PathVariable Long entryId, @RequestBody TimesheetEntryDto  dto) {
        TimesheetResponseDto updatedEntry = timesheetEntryService.updateTimesheetEntry(entryId, dto);
        return ResponseEntity.ok(updatedEntry);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTimesheetEntry(@PathVariable Long id) {
        timesheetEntryService.deleteTimesheetEntry(id);
        return ResponseEntity.ok().build();
    }
}
