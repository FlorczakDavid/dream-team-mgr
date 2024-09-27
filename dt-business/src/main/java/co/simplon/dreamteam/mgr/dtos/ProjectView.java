package co.simplon.dreamteam.mgr.dtos;

import java.util.Collection;
import java.util.Date;


public record ProjectView(
		String name,
		String uniqueInternalId, 
		Date startDate, 
		String description) {

}
