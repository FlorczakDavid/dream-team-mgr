package co.simplon.dreamteam.mgr.dtos;

import java.util.Date;

import co.simplon.dreamteam.mgr.validators.ProjectUniqueInternalId;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ProjectCreate(
		@NotBlank @Size(max= 200) String name, //max 200, not null
		@ProjectUniqueInternalId @NotBlank @Size(max= 100) String uniqueInternalId, //max 100, not null, unique
		@NotNull Date startDate,//not null
		@Size(max= 5000) String description, //max 5k
		String[] langTechNames
) {}
