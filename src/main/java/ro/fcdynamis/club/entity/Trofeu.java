package ro.fcdynamis.club.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "trofee")
public class Trofeu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_trofeu")
    private Long idTrofeu;

    @Column(nullable = false)
    private String nume;

    @ManyToOne
    @JoinColumn(name = "id_competitie")
    private Competitie competitie;

    @Column(nullable = false)
    private int anCastigare;

    // Constructori, getteri, setteri

    public Trofeu() {}

    public Trofeu(String nume, Competitie competitie, int anCastigare) {
        this.nume = nume;
        this.competitie = competitie;
        this.anCastigare = anCastigare;
    }

    public Long getIdTrofeu() {
        return idTrofeu;
    }

    public void setIdTrofeu(Long idTrofeu) {
        this.idTrofeu = idTrofeu;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public Competitie getCompetitie() {
        return competitie;
    }

    public void setCompetitie(Competitie competitie) {
        this.competitie = competitie;
    }

    public int getAnCastigare() {
        return anCastigare;
    }

    public void setAnCastigare(int anCastigare) {
        this.anCastigare = anCastigare;
    }
}
