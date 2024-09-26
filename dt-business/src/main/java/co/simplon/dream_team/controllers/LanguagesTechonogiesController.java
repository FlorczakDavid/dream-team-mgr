package co.simplon.dream_team.controllers;

import java.util.Collection;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.dream_team.dtos.LanguageTechnologyData;
import co.simplon.dream_team.services.ProjectService;

@RestController
@RequestMapping("/langTechs")
@CrossOrigin("*")
public class LanguagesTechonogiesController {
	private final ProjectService service;
	
	public LanguagesTechonogiesController(ProjectService service) {
		this.service = service;
	}
	
	@GetMapping
	Collection<LanguageTechnologyData> getAll() {
		return service.getAll();
	}
	
	@GetMapping("/{name}")
	Collection<LanguageTechnologyData> getByName(@PathVariable("name") String name) {
		return service.getByName(name);
	}

}
