package co.simplon.dream_team.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_languages_technologies")
public class LanguageTechnology {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long langTechId;
	
	@ManyToMany(mappedBy = "usedLangTechs")
	Set<Project> poweredProjects = new HashSet<>();
	
	@Column(name = "lang_tech_name")
	String langTechName;
	
	public LanguageTechnology() {}

	public Long getLangTechId() {
		return langTechId;
	}

	public void setLangTechId(Long langTechId) {
		this.langTechId = langTechId;
	}

	public String getLangTechName() {
		return langTechName;
	}

	public void setLangTechName(String langTechName) {
		this.langTechName = langTechName;
	}

	public Set<Project> getPoweredProjects() {
		return poweredProjects;
	}

	public void setPoweredProjects(Set<Project> poweredProjects) {
		this.poweredProjects = poweredProjects;
	}

	@Override
	public String toString() {
		return "LanguageTechnology [langTechId=" + langTechId + ", langTechName=" + langTechName + "]";
	};
	
	
}
