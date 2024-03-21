package com.example.timesheet.repository;

import com.example.timesheet.model.TimesheetEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimesheetEntryRepository extends JpaRepository<TimesheetEntry, Long> {
}
