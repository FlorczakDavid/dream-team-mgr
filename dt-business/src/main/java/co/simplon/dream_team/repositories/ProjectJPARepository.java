package co.simplon.dream_team.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.simplon.dream_team.entities.Project;

@Repository
public interface ProjectJPARepository extends JpaRepository<Project, Long> {

	boolean existsByProjectUniqueInternalId(String value);
}
