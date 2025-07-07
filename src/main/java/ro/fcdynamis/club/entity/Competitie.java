package ro.fcdynamis.club.entity;

import jakarta.persistence.*;
import java.time.Year;

@Entity
@Table(name = "competitii")
public class Competitie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_competitie")
    private Long idCompetitie;

    @Column(nullable = false)
    private String nume;

    @Column(nullable = false)
    private Year anInceput;

    @Column(nullable = false)
    private boolean europeana; // true dacă este competiție europeană, false dacă este națională

    // Constructori, getteri, setteri

    public Competitie() {}

    public Competitie(String nume, Year anInceput, boolean europeana) {
        this.nume = nume;
        this.anInceput = anInceput;
        this.europeana = europeana;
    }

    public Long getIdCompetitie() {
        return idCompetitie;
    }

    public void setIdCompetitie(Long idCompetitie) {
        this.idCompetitie = idCompetitie;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public Year getAnInceput() {
        return anInceput;
    }

    public void setAnInceput(Year anInceput) {
        this.anInceput = anInceput;
    }

    public boolean isEuropeana() {
        return europeana;
    }

    public void setEuropeana(boolean europeana) {
        this.europeana = europeana;
    }
}
