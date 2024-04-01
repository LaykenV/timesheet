package com.example.timesheet.controller;

import com.example.timesheet.model.Program;
import com.example.timesheet.service.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/programs")
public class ProgramController {

    @Autowired
    private ProgramService programService;

    @CrossOrigin
    @GetMapping
    public List<Program> getAllPrograms() {
        return programService.getAllPrograms();
    }
    
    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<Program> getProgramById(@PathVariable Long id) {
        return ResponseEntity.ok(programService.getProgramById(id));
    }

    @CrossOrigin
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Program> createProgram(@RequestBody Program program) {
        return ResponseEntity.ok(programService.createProgram(program));
    }

    @CrossOrigin
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Program> updateProgram(@PathVariable Long id, @RequestBody Program programDetails) {
        return ResponseEntity.ok(programService.updateProgram(id, programDetails));
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteProgram(@PathVariable Long id) {
        programService.deleteProgram(id);
        return ResponseEntity.ok().build();
    }
}
