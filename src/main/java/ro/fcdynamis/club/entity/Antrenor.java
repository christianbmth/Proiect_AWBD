package ro.fcdynamis.club.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "antrenori")
public class Antrenor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_antrenor")
    private Long idAntrenor;

    @Column(nullable = false)
    private String nume;

    @OneToMany(mappedBy = "antrenor", cascade = CascadeType.ALL)
    private List<Jucator> jucatori;

    // Constructori, getteri, setteri

    public Antrenor() {}

    public Antrenor(String nume) {
        this.nume = nume;
    }

    public Long getIdAntrenor() {
        return idAntrenor;
    }

    public void setIdAntrenor(Long idAntrenor) {
        this.idAntrenor = idAntrenor;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public List<Jucator> getJucatori() {
        return jucatori;
    }

    public void setJucatori(List<Jucator> jucatori) {
        this.jucatori = jucatori;
    }
}
