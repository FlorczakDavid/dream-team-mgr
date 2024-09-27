package co.simplon.dreamteam.mgr.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.dreamteam.mgr.dtos.ProjectCreate;
import co.simplon.dreamteam.mgr.dtos.ProjectToUpdate;
import co.simplon.dreamteam.mgr.dtos.ProjectUpdate;
import co.simplon.dreamteam.mgr.dtos.ProjectView;
import co.simplon.dreamteam.mgr.services.ProjectService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/projects")
@CrossOrigin("*")
public class ProjectController {
	private final ProjectService service;
	
	public ProjectController(ProjectService service) {
		this.service = service;
	}
	
	@GetMapping("/{id}")
	 public ProjectToUpdate getOneProject(@PathVariable("id") Long autoId) {

        // get one project by ID
        return service.getOneProject(autoId);}
	
	@PostMapping("/create")
	ResponseEntity<Object> createProject(@Valid @RequestBody ProjectCreate inputs) {
		service.create(inputs);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/{id}")
    void updateProject(@PathVariable("id") Long autoId, @Valid @RequestBody ProjectUpdate inputs) {
	service.updateProject(autoId, inputs);
    }

}
