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

	boolean existsByProjectUniqueInternalId(String value);

	ProjectView findByProjectUniqueInternalId(String projectUniqueId);

	ProjectView findProjectedByProjectUniqueInternalId(String projectUniqueId);
	
	ProjectView findByProjectId(Long autoId);

	@Query("SELECT new co.simplon.dreamteam.mgr.dtos.LanguageTechnologyData(lt.langTechName) FROM Project p JOIN p.usedLangTechs lt WHERE p.projectId = :autoId")
	Collection<LanguageTechnologyData> findTechnologiesByProjectId(Long autoId);

	Optional<Project> findProjectedByprojectId(Long autoId);
}
