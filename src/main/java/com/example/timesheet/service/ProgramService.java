package com.example.timesheet.service;

import com.example.timesheet.model.Program;
import com.example.timesheet.repository.ProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProgramService {

    @Autowired
    private ProgramRepository programRepository;

    public List<Program> getAllPrograms() {
        return programRepository.findAll();
    }

    public Program getProgramById(Long id) {
        return programRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Program not found with id: " + id));
    }

    @Transactional
    public Program createProgram(Program program) {
        return programRepository.save(program);
    }

    @Transactional
    public Program updateProgram(Long id, Program programDetails) {
        Program program = programRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Program not found with id: " + id));

        program.setName(programDetails.getName());
        program.setDescription(programDetails.getDescription());
        return programRepository.save(program);
    }

    @Transactional
    public void deleteProgram(Long id) {
        Program program = programRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Program not found with id: " + id));
        programRepository.delete(program);
    }
}
