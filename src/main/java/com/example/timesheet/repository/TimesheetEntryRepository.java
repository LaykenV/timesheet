package com.example.timesheet.repository;

import com.example.timesheet.model.TimesheetEntry;
import com.example.timesheet.model.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimesheetEntryRepository extends JpaRepository<TimesheetEntry, Long> {

    List<TimesheetEntry> findByUser(User user);
    

}
