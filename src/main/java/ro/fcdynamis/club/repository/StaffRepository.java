// File: src/main/java/ro/fcdynamis/club/repository/StaffRepository.java
package ro.fcdynamis.club.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.fcdynamis.club.entity.Staff;

public interface StaffRepository extends JpaRepository<Staff, Long> {
    // findAll(Pageable pageable) e deja disponibil
}
