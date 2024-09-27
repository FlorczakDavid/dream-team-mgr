package co.simplon.dreamteam.mgr.dtos;

import java.util.Collection;
import java.util.Date;


public record ProjectView(String projectName,String projectUniqueInternalId, Date projectStartDate, String projectDescription) {

}
