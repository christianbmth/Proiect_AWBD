// File: src/main/java/ro/fcdynamis/club/entity/User.java
package ro.fcdynamis.club.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "useri")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_useri")
    private Long idUser;

    @OneToOne(optional = true)
    @JoinColumn(name = "id_staff", referencedColumnName = "id_staff")
    private Staff staff;

    @NotBlank(message = "Email-ul este obligatoriu")
    @Email(message = "Trebuie să fie un email valid")
    @Size(max = 100, message = "Email-ul poate avea maxim 100 caractere")
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @NotBlank(message = "Parola este obligatorie")
    @Size(min = 6, max = 50, message = "Parola trebuie să aibă între 6 și 50 caractere")
    @Column(name = "parola", nullable = false)
    private String parola;

    public User() {}

    public User(Staff staff, String username, String parola) {
        this.staff    = staff;
        this.username = username;
        this.parola   = parola;
    }

    // Getteri și setteri...
    public Long getIdUser() { return idUser; }
    public void setIdUser(Long idUser) { this.idUser = idUser; }
    public Staff getStaff() { return staff; }
    public void setStaff(Staff staff) { this.staff = staff; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getParola() { return parola; }
    public void setParola(String parola) { this.parola = parola; }
}
