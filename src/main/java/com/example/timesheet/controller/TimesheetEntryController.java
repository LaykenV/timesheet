package com.example.timesheet.controller;

import com.example.timesheet.model.TimesheetEntry;
import com.example.timesheet.service.TimesheetEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/timesheetEntries")
public class TimesheetEntryController {

    @Autowired
    private TimesheetEntryService timesheetEntryService;

    @GetMapping
    public ResponseEntity<List<TimesheetEntry>> getAllTimesheetEntries() {
        return ResponseEntity.ok(timesheetEntryService.getAllTimesheetEntries());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TimesheetEntry> getTimesheetEntryById(@PathVariable Long id) {
        return ResponseEntity.ok(timesheetEntryService.getTimesheetEntryById(id));
    }

    @PostMapping
    public ResponseEntity<TimesheetEntry> createTimesheetEntry(@RequestBody TimesheetEntry timesheetEntry) {
        return new ResponseEntity<>(timesheetEntryService.createTimesheetEntry(timesheetEntry), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TimesheetEntry> updateTimesheetEntry(@PathVariable Long id, @RequestBody TimesheetEntry timesheetEntryDetails) {
        return ResponseEntity.ok(timesheetEntryService.updateTimesheetEntry(id, timesheetEntryDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTimesheetEntry(@PathVariable Long id) {
        timesheetEntryService.deleteTimesheetEntry(id);
        return ResponseEntity.ok().build();
    }
}
