package it.begear.banca.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.begear.banca.entity.Azienda;
import it.begear.banca.entity.Persona;

public interface PersonaRepository extends JpaRepository<Persona, Integer> {
	
	@Query("SELECT p FROM Persona p WHERE p.cf LIKE %?1%")
	public Persona searchCF(String s);
	


}
