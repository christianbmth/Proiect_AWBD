package ro.fcdynamis.club.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.fcdynamis.club.entity.Jucator;
import ro.fcdynamis.club.repository.JucatorRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/jucatori")
public class JucatorController {

    @Autowired
    private JucatorRepository jucatorRepository;

    @GetMapping
    public List<Jucator> getAllJucatori() {
        return jucatorRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Jucator> getJucatorById(@PathVariable Long id) {
        Optional<Jucator> jucator = jucatorRepository.findById(id);
        return jucator.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Jucator createJucator(@RequestBody Jucator jucator) {
        return jucatorRepository.save(jucator);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJucator(@PathVariable Long id) {
        if (!jucatorRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        jucatorRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
