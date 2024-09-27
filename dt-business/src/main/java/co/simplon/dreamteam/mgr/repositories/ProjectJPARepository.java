package co.simplon.dreamteam.mgr.repositories;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.simplon.dreamteam.mgr.dtos.LanguageTechnologyData;
import co.simplon.dreamteam.mgr.dtos.ProjectView;
import co.simplon.dreamteam.mgr.entities.Project;

@Repository
public interface ProjectJPARepository extends JpaRepository<Project, Long> {

	boolean existsByUniqueInternalId(String value);

	ProjectView findByUniqueInternalId(String projectUniqueId);

	ProjectView findProjectedByUniqueInternalId(String projectUniqueId);
	
	ProjectView findProjectedById(Long autoId);

	@Query("SELECT new co.simplon.dreamteam.mgr.dtos.LanguageTechnologyData(lt.name) FROM Project p JOIN p.usedLangTechs lt WHERE p.id = :autoId")
	Collection<LanguageTechnologyData> findTechnologiesByProjectId(Long autoId);

	Optional<Project> findById(Long autoId);
}
