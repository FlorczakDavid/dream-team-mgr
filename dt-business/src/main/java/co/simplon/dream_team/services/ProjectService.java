package co.simplon.dream_team.services;

import java.util.Collection;

import org.springframework.stereotype.Service;

import co.simplon.dream_team.dtos.LanguageTechnologyData;
import co.simplon.dream_team.dtos.ProjectCreate;
import co.simplon.dream_team.entities.LanguageTechnology;
import co.simplon.dream_team.entities.Project;
import co.simplon.dream_team.repositories.LanguagesTechnologiesJPARepository;
import co.simplon.dream_team.repositories.ProjectJPARepository;
import jakarta.validation.Valid;

@Service
public class ProjectService {

	private final ProjectJPARepository projects;
	private final LanguagesTechnologiesJPARepository langTechs;
	
	public ProjectService(ProjectJPARepository projects, LanguagesTechnologiesJPARepository langTechs) {
		this.projects = projects;
		this.langTechs = langTechs;
	}
	
	public void create(@Valid ProjectCreate inputs) {
		Project project = new Project();
		project.setProjectName(inputs.projectName());
		project.setProjectUniqueInternalId(inputs.projectUniqueInternalId());
		project.setProjectStartDate(inputs.projectStartDate());
		project.setProjectDescription(inputs.projectDescription());
		
		String[] langTechNames = inputs.langTechNames();
		for (String langTechName : langTechNames) {
			LanguageTechnology langTech = langTechs.findByLangTechNameIgnoreCase(langTechName);
			if(langTech == null) { //create
				langTech = new LanguageTechnology();
				langTech.setLangTechName(langTechName);
				langTechs.save(langTech);
			}
			
		}
		projects.save(project);
	}

	public boolean existsByProjectUniqueInternalId(String value) {
		return projects.existsByProjectUniqueInternalId(value);
	}

	public Collection<LanguageTechnologyData> getAll() {
		return langTechs.findProjectedBy();
	}
	
	
}
