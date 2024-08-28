package co.simplon.dream_team.validators;

import co.simplon.dream_team.services.ProjectService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueProjectInternalIdValidator implements ConstraintValidator<UniqueProjectInternalId, String>{

	private final ProjectService service;
	
	public UniqueProjectInternalIdValidator(ProjectService service) {
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
