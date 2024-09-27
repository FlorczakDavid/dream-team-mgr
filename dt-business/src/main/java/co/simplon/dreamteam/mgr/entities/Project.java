package co.simplon.dreamteam.mgr.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_projects")
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToMany
	@JoinTable(
		name="t_use",
		joinColumns = @JoinColumn(name = "project_id"),
		inverseJoinColumns = @JoinColumn(name = "lang_tech_id"))
	Set<LanguageTechnology> usedLangTechs = new HashSet<>();

	@Column(name = "name")
	String name;

	@Column(name = "unique_internal_identifier")
	String uniqueInternalId;

	@Column(name = "start_date")
	Date startDate;

	@Column(name = "description")
	String description;
	
	
	public Project() {}


	public Long getId() {
		return id;
	}


	public void setId(Long project_id) {
		this.id = project_id;
	}


	public String getName() {
		return name;
	}


	public void setName(String projectName) {
		this.name = projectName;
	}


	public String getUniqueInternalId() {
		return uniqueInternalId;
	}


	public void setUniqueInternalId(String projectUniqueInternalId) {
		this.uniqueInternalId = projectUniqueInternalId;
	}


	public Date getStartDate() {
		return startDate;
	}


	public void setStartDate(Date projectStartDate) {
		this.startDate = projectStartDate;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String projectDescription) {
		this.description = projectDescription;
	}
	

	public Set<LanguageTechnology> getUsedLangTechs() {
		return usedLangTechs;
	}


	public void setUsedLangTechs(Set<LanguageTechnology> usedLangTechs) {
		this.usedLangTechs = usedLangTechs;
	}


	@Override
	public String toString() {
		return "Project [id=" + id + ", projectName=" + name + ", projectUniqueInternalId="
				+ uniqueInternalId + ", projectStartDate=" + startDate + ", projectDescription="
				+ description + "]";
	}
	
	
}
