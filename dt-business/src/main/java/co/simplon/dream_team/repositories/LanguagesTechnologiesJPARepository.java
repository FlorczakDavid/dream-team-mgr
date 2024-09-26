package co.simplon.dream_team.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.simplon.dream_team.dtos.LanguageTechnologyData;
import co.simplon.dream_team.entities.LanguageTechnology;

@Repository
public interface LanguagesTechnologiesJPARepository extends JpaRepository<LanguageTechnology, Long>{

	LanguageTechnology findByLangTechNameIgnoreCase(String langTechName);

	Collection<LanguageTechnologyData> findProjectedBy();
	
	LanguageTechnologyData findByLangTechId(Long id);//query 
	
}
