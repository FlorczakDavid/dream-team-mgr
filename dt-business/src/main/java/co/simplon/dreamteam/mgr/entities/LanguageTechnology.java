package co.simplon.dreamteam.mgr.entities;

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
	private Long id;
	
	@ManyToMany(mappedBy = "usedLangTechs")
	Set<Project> poweredProjects = new HashSet<>();
	
	@Column(name = "name")
	String name;
	
	public LanguageTechnology() { /* TODO document why this constructor is empty */ }

	public Long getId() {
		return id;
	}

	public void setId(Long langTechId) {
		this.id = langTechId;
	}

	public String getName() {
		return name;
	}

	public void setName(String langTechName) {
		this.name = langTechName;
	}

	public Set<Project> getPoweredProjects() {
		return poweredProjects;
	}

	public void setPoweredProjects(Set<Project> poweredProjects) {
		this.poweredProjects = poweredProjects;
	}

	@Override
	public String toString() {
		return "LanguageTechnology [langTechId=" + id + ", langTechName=" + name + "]";
	}
	
	
}
