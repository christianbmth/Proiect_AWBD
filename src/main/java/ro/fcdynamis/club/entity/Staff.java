// File: src/main/java/ro/fcdynamis/club/entity/Staff.java
package ro.fcdynamis.club.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "staff")
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_staff")
    private Long idStaff;

    @NotBlank(message = "Prenumele este obligatoriu")
    @Size(max = 50, message = "Prenumele poate avea maxim 50 caractere")
    @Column(name = "prenume", nullable = false)
    private String prenume;

    @NotBlank(message = "Numele este obligatoriu")
    @Size(max = 50, message = "Numele poate avea maxim 50 caractere")
    @Column(name = "nume", nullable = false)
    private String nume;

    @NotBlank(message = "Funcția este obligatorie")
    @Size(max = 50, message = "Funcția poate avea maxim 50 caractere")
    @Column(name = "functie", nullable = false)
    private String functie;

    public Staff() {}

    public Staff(String prenume, String nume, String functie) {
        this.prenume = prenume;
        this.nume = nume;
        this.functie = functie;
    }

    // Getteri și setteri...
    public Long getIdStaff() { return idStaff; }
    public void setIdStaff(Long idStaff) { this.idStaff = idStaff; }
    public String getPrenume() { return prenume; }
    public void setPrenume(String prenume) { this.prenume = prenume; }
    public String getNume() { return nume; }
    public void setNume(String nume) { this.nume = nume; }
    public String getFunctie() { return functie; }
    public void setFunctie(String functie) { this.functie = functie; }
}
