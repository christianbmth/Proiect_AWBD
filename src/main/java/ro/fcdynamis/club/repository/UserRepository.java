// File: src/main/java/ro/fcdynamis/club/repository/UserRepository.java
package ro.fcdynamis.club.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.fcdynamis.club.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    // findAll(Pageable pageable) e deja disponibil
}
