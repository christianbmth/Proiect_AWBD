// src/test/java/ro/fcdynamis/club/repository/StaffRepositoryTest.java
package ro.fcdynamis.club.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import ro.fcdynamis.club.entity.Staff;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class StaffRepositoryTest {

    @Autowired
    private StaffRepository repo;

    @Test
    void testSaveAndFindAll() {
        Staff s = new Staff("Ion", "Pop", "Antrenor");
        repo.save(s);

        List<Staff> all = repo.findAll();
        assertThat(all).hasSize(1)
                       .first()
                       .extracting(Staff::getPrenume, Staff::getNume, Staff::getFunctie)
                       .containsExactly("Ion", "Pop", "Antrenor");
    }
}
