package co.simplon.dreamteam.mgr.validators;

import co.simplon.dreamteam.mgr.services.ProjectService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ProjectUniqueInternalIdValidator implements ConstraintValidator<ProjectUniqueInternalId, String>{

	private final ProjectService service;
	
	public ProjectUniqueInternalIdValidator(ProjectService service) {
		this.service = service;
	}
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null) {
			return true;
		}
		return !service.existsByProjectUniqueInternalId(value);
	}

}
