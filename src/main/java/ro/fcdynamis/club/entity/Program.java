package ro.fcdynamis.club.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "program")
public class Program {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_program")
    private Long idProgram;

    @Column(nullable = false)
    private LocalDate dataMeci;

    @Column(nullable = false)
    private String adversar;

    @Column
    private String locatie;  // ex: "Acasa", "Deplasare"

    @Column
    private Integer scorEchipa;

    @Column
    private Integer scorAdversar;

    @ManyToMany(mappedBy = "programe")
    private List<Jucator> jucatori;

    // Constructori, getteri, setteri

    public Program() {}

    public Program(LocalDate dataMeci, String adversar, String locatie, Integer scorEchipa, Integer scorAdversar) {
        this.dataMeci = dataMeci;
        this.adversar = adversar;
        this.locatie = locatie;
        this.scorEchipa = scorEchipa;
        this.scorAdversar = scorAdversar;
    }

    public Long getIdProgram() {
        return idProgram;
    }

    public void setIdProgram(Long idProgram) {
        this.idProgram = idProgram;
    }

    public LocalDate getDataMeci() {
        return dataMeci;
    }

    public void setDataMeci(LocalDate dataMeci) {
        this.dataMeci = dataMeci;
    }

    public String getAdversar() {
        return adversar;
    }

    public void setAdversar(String adversar) {
        this.adversar = adversar;
    }

    public String getLocatie() {
        return locatie;
    }

    public void setLocatie(String locatie) {
        this.locatie = locatie;
    }

    public Integer getScorEchipa() {
        return scorEchipa;
    }

    public void setScorEchipa(Integer scorEchipa) {
        this.scorEchipa = scorEchipa;
    }

    public Integer getScorAdversar() {
        return scorAdversar;
    }

    public void setScorAdversar(Integer scorAdversar) {
        this.scorAdversar = scorAdversar;
    }

    public List<Jucator> getJucatori() {
        return jucatori;
    }

    public void setJucatori(List<Jucator> jucatori) {
        this.jucatori = jucatori;
    }
}
