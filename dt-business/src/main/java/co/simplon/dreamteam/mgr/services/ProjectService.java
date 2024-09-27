package co.simplon.dreamteam.mgr.services;

import java.util.Collection;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import co.simplon.dreamteam.mgr.dtos.LanguageTechnologyData;
import co.simplon.dreamteam.mgr.dtos.ProjectCreate;
import co.simplon.dreamteam.mgr.dtos.ProjectToUpdate;
import co.simplon.dreamteam.mgr.dtos.ProjectUpdate;
import co.simplon.dreamteam.mgr.dtos.ProjectView;
import co.simplon.dreamteam.mgr.entities.LanguageTechnology;
import co.simplon.dreamteam.mgr.entities.Project;
import co.simplon.dreamteam.mgr.exceptions.LanguageTechnologyInvalidNameException;
import co.simplon.dreamteam.mgr.repositories.LanguageTechnologyJPARepository;
import co.simplon.dreamteam.mgr.repositories.ProjectJPARepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class ProjectService {

	private final ProjectJPARepository projects;
	private final LanguageTechnologyJPARepository langTechs;
	
	public ProjectService(ProjectJPARepository projects, LanguageTechnologyJPARepository langTechs) {
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
	            throw new LanguageTechnologyInvalidNameException("Language technology name cannot be empty.");
	        }
	        
			LanguageTechnology langTech = langTechs.findByNameIgnoreCase(langTechName.trim());
			if(langTech == null) { //create
				langTech = new LanguageTechnology();
				langTech.setLangTechName(langTechName);
				langTechs.save(langTech);
			}
			project.getUsedLangTechs().add(langTech);
			
		}
		projects.save(project);
	}
	
	public ProjectToUpdate getOneProject(Long autoid) {
		ProjectView oneProject;
		//System.out.println(projects.findByProjectUniqueInternalId(projectUniqueId));
		oneProject=projects.findProjectedById(autoid);
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
		return projects.existsByUniqueInternalId(value);
	}

	public Collection<LanguageTechnologyData> getByName(String name) {
		return langTechs.findTop5ByNameStartsWithIgnoreCaseOrderByNameAsc(name);
	}

	public Collection<LanguageTechnologyData> getAll() {
		return langTechs.findProjectedBy();
	}
	
	public void updateProject(Long autoId, @Valid ProjectUpdate inputs) {
		Project entity = projects.findById(autoId).get();
	        entity.setProjectName(inputs.projectName());
	        entity.setProjectDescription(inputs.projectDescription());
	        entity.setProjectStartDate(inputs.projectStartDate());
	        entity.setProjectUniqueInternalId(inputs.projectUniqueInternalId());
	        
	        String[] langTechNames = inputs.langTechNames();
	        entity.getUsedLangTechs().clear();
			for (String langTechName : langTechNames) {
				langTechName = langTechName.trim();
		        if (langTechName == null || langTechName.isEmpty()) {
		            throw new LanguageTechnologyInvalidNameException("Language technology name cannot be empty.");
		        }
		        
				LanguageTechnology langTech = langTechs.findByNameIgnoreCase(langTechName);
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
