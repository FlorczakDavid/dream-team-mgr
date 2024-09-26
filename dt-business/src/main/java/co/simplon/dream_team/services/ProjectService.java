package co.simplon.dream_team.services;

import java.util.Collection;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import co.simplon.dream_team.dtos.LanguageTechnologyData;
import co.simplon.dream_team.dtos.ProjectCreate;
import co.simplon.dream_team.dtos.ProjectToUpdate;
import co.simplon.dream_team.dtos.ProjectUpdate;
import co.simplon.dream_team.dtos.ProjectView;
import co.simplon.dream_team.dtos.UsedLangTechsProjection;
import co.simplon.dream_team.entities.LanguageTechnology;
import co.simplon.dream_team.entities.Project;
import co.simplon.dream_team.exceptions.InvalidLanguageTechnologyNameException;
import co.simplon.dream_team.repositories.LanguagesTechnologiesJPARepository;
import co.simplon.dream_team.repositories.ProjectJPARepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class ProjectService {

	private final ProjectJPARepository projects;
	private final LanguagesTechnologiesJPARepository langTechs;
	
	public ProjectService(ProjectJPARepository projects, LanguagesTechnologiesJPARepository langTechs) {
		this.projects = projects;
		this.langTechs = langTechs;
	}
	
	@Transactional
	public void create(@Valid ProjectCreate inputs) {
		Project project = new Project();
		project.setProjectName(inputs.projectName());
		project.setProjectUniqueInternalId(inputs.projectUniqueInternalId());
		project.setProjectStartDate(inputs.projectStartDate());
		project.setProjectDescription(inputs.projectDescription());
		
		String[] langTechNames = inputs.langTechNames();
		for (String langTechName : langTechNames) {
			langTechName = langTechName.trim();
	        if (langTechName == null || langTechName.isEmpty()) {
	            throw new InvalidLanguageTechnologyNameException("Language technology name cannot be empty.");
	        }
	        
			LanguageTechnology langTech = langTechs.findByLangTechNameIgnoreCase(langTechName);
			if(langTech == null) { //create
				langTech = new LanguageTechnology();
				langTech.setLangTechName(langTechName);
				langTechs.save(langTech);
			}
			project.getUsedLangTechs().add(langTech);
			
		}
		projects.save(project);
	}
	
	public ProjectToUpdate getProjectOne(Long autoid) {
		ProjectView oneProject;
		//System.out.println(projects.findByProjectUniqueInternalId(projectUniqueId));
		oneProject=projects.findByProjectId(autoid);
		//System.out.println(projects.findUsedLangTechsByProjectId());
		Collection <LanguageTechnologyData> oneLangTechProj;
		oneLangTechProj = projects.findTechnologiesByProjectId(autoid);
		//oneProject.setusedLangTechs(oneLangTechProj);
		//oneLangTechProj= projects.findByprojectId(autoid);
		
		//@Query("SELECT l.usedLangTechs FROM Project l WHERE l.projectId = :id")
		//Collection<LanguageTechnologyData> findUsedLangTechsById(@Param("id") Long id);
		
		//oneLangTechProj = findUsedLangTechsById;
		
//		String thisID = projects.findProjectedByProjectUniqueInternalId(projectUniqueId)
		
		ProjectToUpdate projectToUpdate= new ProjectToUpdate(oneProject, oneLangTechProj);
		return projectToUpdate;
	}

	public boolean existsByProjectUniqueInternalId(String value) {
		return projects.existsByProjectUniqueInternalId(value);
	}

	public Collection<LanguageTechnologyData> getAll() {
		return langTechs.findProjectedBy();
	}
	
	public void updateProject(Long autoId, @Valid ProjectUpdate inputs) {
		Project entity = projects.findProjectedByprojectId(autoId).get();
	        entity.setProjectName(inputs.projectName());
	        entity.setProjectDescription(inputs.projectDescription());
	        entity.setProjectStartDate(inputs.projectStartDate());
	        entity.setProjectUniqueInternalId(inputs.projectUniqueInternalId());
	        
	        String[] langTechNames = inputs.langTechNames();
	        entity.getUsedLangTechs().clear();
			for (String langTechName : langTechNames) {
				langTechName = langTechName.trim();
		        if (langTechName == null || langTechName.isEmpty()) {
		            throw new InvalidLanguageTechnologyNameException("Language technology name cannot be empty.");
		        }
		        
				LanguageTechnology langTech = langTechs.findByLangTechNameIgnoreCase(langTechName);
				if(langTech == null) { //create
					langTech = new LanguageTechnology();
					langTech.setLangTechName(langTechName);
					langTechs.save(langTech);
					//entity.getUsedLangTechs().add(langTech);
				}
				
				
				//LanguageTechnology tempLT = new LanguageTechnology()
				//entity.setUsedLangTechs(langTech);
				entity.getUsedLangTechs().add(langTech);
				
			}

		projects.save(entity);

	    }
	
	
}
