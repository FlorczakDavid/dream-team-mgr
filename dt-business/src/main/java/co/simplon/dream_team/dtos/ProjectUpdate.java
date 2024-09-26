package co.simplon.dream_team.dtos;

import java.util.Date;

import co.simplon.dream_team.validators.UniqueProjectInternalId;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ProjectUpdate(
		@NotBlank @Size(max= 200) String projectName, //max 200, not null
		//@UniqueProjectInternalId 
		@NotBlank @Size(max= 100) String projectUniqueInternalId, //max 100, not null, unique
		@NotNull Date projectStartDate,//not null
		@Size(max= 5000) String projectDescription, //max 5k
		String[] langTechNames
) {}
