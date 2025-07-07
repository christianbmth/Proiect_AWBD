package ro.fcdynamis.club.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.fcdynamis.club.entity.Antrenor;
import ro.fcdynamis.club.repository.AntrenorRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/antrenori")
public class AntrenorController {

    @Autowired
    private AntrenorRepository antrenorRepository;

    @GetMapping
    public List<Antrenor> getAllAntrenori() {
        return antrenorRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Antrenor> getAntrenorById(@PathVariable Long id) {
        Optional<Antrenor> antrenor = antrenorRepository.findById(id);
        return antrenor.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Antrenor createAntrenor(@RequestBody Antrenor antrenor) {
        return antrenorRepository.save(antrenor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAntrenor(@PathVariable Long id) {
        if (!antrenorRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        antrenorRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
