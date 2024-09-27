package co.simplon.dreamteam.mgr.dtos;

import java.util.Collection;

public record ProjectToUpdate(ProjectView oneProject, Collection <LanguageTechnologyData> oneLangTechProj) {

}
