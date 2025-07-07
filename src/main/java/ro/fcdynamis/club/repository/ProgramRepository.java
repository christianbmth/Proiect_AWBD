package ro.fcdynamis.club.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.fcdynamis.club.entity.Program;

public interface ProgramRepository extends JpaRepository<Program, Long> {
}
