package co.simplon.dreamteam.mgr.controllers;

import java.util.Collection;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.dreamteam.mgr.dtos.LanguageTechnologyData;
import co.simplon.dreamteam.mgr.services.ProjectService;

@RestController
@RequestMapping("/langTechs")
@CrossOrigin("*")
public class LanguageTechonologyController {
	private final ProjectService service;
	
	public LanguageTechonologyController(ProjectService service) {
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
