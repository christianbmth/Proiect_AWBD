package ro.fcdynamis.club.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.fcdynamis.club.entity.Program;
import ro.fcdynamis.club.repository.ProgramRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/program")
public class ProgramController {

    @Autowired
    private ProgramRepository programRepository;

    @GetMapping
    public List<Program> getAllProgram() {
        return programRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Program> getProgramById(@PathVariable Long id) {
        Optional<Program> program = programRepository.findById(id);
        return program.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Program createProgram(@RequestBody Program program) {
        return programRepository.save(program);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProgram(@PathVariable Long id) {
        if (!programRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        programRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
