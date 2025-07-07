// src/test/java/ro/fcdynamis/club/repository/UserRepositoryTest.java
package ro.fcdynamis.club.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ro.fcdynamis.club.entity.Staff;
import ro.fcdynamis.club.entity.User;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager em;

    @Autowired
    private UserRepository repo;

    @Test
    void testSaveAndFindAll() {
        // persistăm mai întâi un Staff, pentru a avea cheia străină
        Staff s = new Staff("Ion", "Pop", "Antrenor");
        em.persistAndFlush(s);

        // folosim o parolă validă (6–50 caractere)
        User u = new User(s, "x@y.z", "secret1");
        repo.save(u);

        List<User> all = repo.findAll();
        assertThat(all)
          .hasSize(1)
          .first()
          .extracting(User::getUsername)
          .isEqualTo("x@y.z");
    }
}
