package ro.fcdynamis.club.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "jucatori")
public class Jucator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_jucator")
    private Long idJucator;

    @Column(nullable = false)
    private String nume;

    @ManyToOne
    @JoinColumn(name = "id_antrenor")
    private Antrenor antrenor;

    @ManyToMany
    @JoinTable(
        name = "program_jucatori",
        joinColumns = @JoinColumn(name = "id_jucator"),
        inverseJoinColumns = @JoinColumn(name = "id_program")
    )
    private List<Program> programe;

    // Constructori, getteri, setteri

    public Jucator() {}

    public Jucator(String nume, Antrenor antrenor) {
        this.nume = nume;
        this.antrenor = antrenor;
    }

    public Long getIdJucator() {
        return idJucator;
    }

    public void setIdJucator(Long idJucator) {
        this.idJucator = idJucator;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public Antrenor getAntrenor() {
        return antrenor;
    }

    public void setAntrenor(Antrenor antrenor) {
        this.antrenor = antrenor;
    }

    public List<Program> getPrograme() {
        return programe;
    }

    public void setPrograme(List<Program> programe) {
        this.programe = programe;
    }
}
