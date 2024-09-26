package co.simplon.dream_team.entities;

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
	private Long projectId;
	
	@ManyToMany
	@JoinTable(
		name="t_use",
		joinColumns = @JoinColumn(name = "project_id"),
		inverseJoinColumns = @JoinColumn(name = "lang_tech_id"))
	Set<LanguageTechnology> usedLangTechs = new HashSet<>();

	@Column(name = "project_name")
	String projectName;

	@Column(name = "project_unique_internal_identifier")
	String projectUniqueInternalId;

	@Column(name = "project_start_date")
	Date projectStartDate;

	@Column(name = "project_description")
	String projectDescription;
	
	
	public Project() {}


	public Long getId() {
		return projectId;
	}


	public void setId(Long project_id) {
		this.projectId = project_id;
	}


	public String getProjectName() {
		return projectName;
	}


	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}


	public String getProjectUniqueInternalId() {
		return projectUniqueInternalId;
	}


	public void setProjectUniqueInternalId(String projectUniqueInternalId) {
		this.projectUniqueInternalId = projectUniqueInternalId;
	}


	public Date getProjectStartDate() {
		return projectStartDate;
	}


	public void setProjectStartDate(Date projectStartDate) {
		this.projectStartDate = projectStartDate;
	}


	public String getProjectDescription() {
		return projectDescription;
	}


	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}
	

	public Set<LanguageTechnology> getUsedLangTechs() {
		return usedLangTechs;
	}


	public void setUsedLangTechs(Set<LanguageTechnology> usedLangTechs) {
		this.usedLangTechs = usedLangTechs;
	}


	@Override
	public String toString() {
		return "Project [id=" + projectId + ", projectName=" + projectName + ", projectUniqueInternalId="
				+ projectUniqueInternalId + ", projectStartDate=" + projectStartDate + ", projectDescription="
				+ projectDescription + "]";
	}


	public void setUsedLangTechs(LanguageTechnology langTech) {
		// TODO Auto-generated method stub
		
	}
	
	
}
