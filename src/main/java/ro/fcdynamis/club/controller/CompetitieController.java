package ro.fcdynamis.club.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.fcdynamis.club.entity.Competitie;
import ro.fcdynamis.club.repository.CompetitieRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/competitii")
public class CompetitieController {

    @Autowired
    private CompetitieRepository competitieRepository;

    @GetMapping
    public List<Competitie> getAllCompetitii() {
        return competitieRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Competitie> getCompetitieById(@PathVariable Long id) {
        Optional<Competitie> competitie = competitieRepository.findById(id);
        return competitie.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Competitie createCompetitie(@RequestBody Competitie competitie) {
        return competitieRepository.save(competitie);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompetitie(@PathVariable Long id) {
        if (!competitieRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        competitieRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
