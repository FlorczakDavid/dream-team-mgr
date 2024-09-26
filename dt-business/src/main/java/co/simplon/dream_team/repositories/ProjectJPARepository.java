package co.simplon.dream_team.repositories;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.simplon.dream_team.dtos.LanguageTechnologyData;
import co.simplon.dream_team.dtos.ProjectView;
import co.simplon.dream_team.dtos.UsedLangTechsProjection;
import co.simplon.dream_team.entities.Project;

@Repository
public interface ProjectJPARepository extends JpaRepository<Project, Long> {

	boolean existsByProjectUniqueInternalId(String value);

	ProjectView findByProjectUniqueInternalId(String projectUniqueId);

	ProjectView findProjectedByProjectUniqueInternalId(String projectUniqueId);
	
	// @Query("select p.projectName, p.xxx from Project p where p.projectId = :autoId")
	ProjectView findByProjectId(Long autoId);

	@Query("SELECT new co.simplon.dream_team.dtos.LanguageTechnologyData(lt.langTechName) FROM Project p JOIN p.usedLangTechs lt WHERE p.projectId = :autoId")
	Collection<LanguageTechnologyData> findTechnologiesByProjectId(Long autoId);

	Optional<Project> findProjectedByprojectId(Long autoId);
	
	Collection<UsedLangTechsProjection> findProjectedById(Long autoId);

	//Collection<LanguageTechnologyData> findUsedLangTechsByProjectId();
}
