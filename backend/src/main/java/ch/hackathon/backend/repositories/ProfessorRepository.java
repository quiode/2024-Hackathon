package ch.hackathon.backend.repositories;

import ch.hackathon.backend.models.Professor;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
  Optional<Professor> findByName(String name);
}
