package co.simplon.dreamteam.mgr.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.simplon.dreamteam.mgr.dtos.LanguageTechnologyData;
import co.simplon.dreamteam.mgr.entities.LanguageTechnology;

@Repository
public interface LanguageTechnologyJPARepository extends JpaRepository<LanguageTechnology, Long>{

	LanguageTechnology findByLangTechNameIgnoreCase(String langTechName);

	Collection<LanguageTechnologyData> findProjectedBy();
	
	LanguageTechnologyData findByLangTechId(Long id);//query 

	Collection<LanguageTechnologyData> findTop5ByLangTechNameStartsWithIgnoreCaseOrderByLangTechNameAsc(String langTechName);
	
}
