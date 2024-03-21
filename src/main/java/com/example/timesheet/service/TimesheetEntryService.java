package com.example.timesheet.service;

import com.example.timesheet.model.TimesheetEntry;
import com.example.timesheet.repository.TimesheetEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TimesheetEntryService {

    @Autowired
    private TimesheetEntryRepository timesheetEntryRepository;

    public List<TimesheetEntry> getAllTimesheetEntries() {
        return timesheetEntryRepository.findAll();
    }

    public TimesheetEntry getTimesheetEntryById(Long id) {
        Optional<TimesheetEntry> timesheetEntry = timesheetEntryRepository.findById(id);
        if (timesheetEntry.isPresent()) {
            return timesheetEntry.get();
        } else {
            throw new RuntimeException("Timesheet entry not found for id :: " + id);
        }
    }

    public TimesheetEntry createTimesheetEntry(TimesheetEntry timesheetEntry) {
        return timesheetEntryRepository.save(timesheetEntry);
    }

    public TimesheetEntry updateTimesheetEntry(Long id, TimesheetEntry timesheetEntryDetails) {
        TimesheetEntry timesheetEntry = getTimesheetEntryById(id);

        timesheetEntry.setUser(timesheetEntryDetails.getUser());
        timesheetEntry.setProgram(timesheetEntryDetails.getProgram());
        timesheetEntry.setDate(timesheetEntryDetails.getDate());
        timesheetEntry.setHours(timesheetEntryDetails.getHours());
        timesheetEntry.setDescription(timesheetEntryDetails.getDescription());

        return timesheetEntryRepository.save(timesheetEntry);
    }

    public void deleteTimesheetEntry(Long id) {
        TimesheetEntry timesheetEntry = getTimesheetEntryById(id);
        timesheetEntryRepository.delete(timesheetEntry);
    }
}
