package co.simplon.dream_team.services;

import org.springframework.stereotype.Service;

import co.simplon.dream_team.dtos.ProjectCreate;
import co.simplon.dream_team.entities.Project;
import co.simplon.dream_team.repositories.ProjectJPARepository;
import jakarta.validation.Valid;

@Service
public class ProjectService {

	private final ProjectJPARepository repository;
	
	public ProjectService(ProjectJPARepository repository) {
		this.repository = repository;
	}
	
	public void create(@Valid ProjectCreate inputs) {
		Project project = new Project();
		project.setProjectName(inputs.projectName());
		project.setProjectUniqueInternalId(inputs.projectUniqueInternalId());
		project.setProjectStartDate(inputs.projectStartDate());
		project.setProjectDescription(inputs.projectDescription());
		repository.save(project);
	}

	public boolean existsByProjectUniqueInternalId(String value) {
		return repository.existsByProjectUniqueInternalId(value);
	}
	
	
}
