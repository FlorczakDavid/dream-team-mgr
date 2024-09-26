package co.simplon.dream_team.dtos;

import java.util.Collection;

public record ProjectToUpdate(ProjectView oneProject, Collection <LanguageTechnologyData> oneLangTechProj) {

}
