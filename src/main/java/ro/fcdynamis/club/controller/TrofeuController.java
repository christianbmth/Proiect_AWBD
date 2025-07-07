package ro.fcdynamis.club.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.fcdynamis.club.entity.Trofeu;
import ro.fcdynamis.club.repository.TrofeuRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/trofee")
public class TrofeuController {

    @Autowired
    private TrofeuRepository trofeuRepository;

    @GetMapping
    public List<Trofeu> getAllTrofee() {
        return trofeuRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Trofeu> getTrofeuById(@PathVariable Long id) {
        Optional<Trofeu> trofeu = trofeuRepository.findById(id);
        return trofeu.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Trofeu createTrofeu(@RequestBody Trofeu trofeu) {
        return trofeuRepository.save(trofeu);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrofeu(@PathVariable Long id) {
        if (!trofeuRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        trofeuRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
