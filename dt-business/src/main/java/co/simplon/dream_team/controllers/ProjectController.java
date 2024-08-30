package co.simplon.dream_team.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.dream_team.dtos.ProjectCreate;
import co.simplon.dream_team.services.ProjectService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/projects")
@CrossOrigin("*")
public class ProjectController {
	private final ProjectService service;
	
	public ProjectController(ProjectService service) {
		this.service = service;
	}
	
	@PostMapping("/create")
	ResponseEntity<Object> createProject(@Valid @RequestBody ProjectCreate inputs) {
		service.create(inputs);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}

}
