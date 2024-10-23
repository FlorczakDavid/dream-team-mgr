package co.simplon.dreamteam.mgr.validators;

import co.simplon.dreamteam.mgr.services.ProjectService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ProjectUpdateUniqueInternalIdValidator implements ConstraintValidator<ProjectUpdateUniqueInternalId, String>{

	private final ProjectService service;
	
	public ProjectUpdateUniqueInternalIdValidator(ProjectService service) {
		this.service = service;
	}
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {

		String id = ValidationUtils.pathVariableAsLong("id").toString();
		
		if (value == null) {
			return true;
		}
		if (service.existsByProjectUniqueInternalId(id)) {
			return true;
		}
		return !service.existsByProjectUniqueInternalId(value);
	}

}
