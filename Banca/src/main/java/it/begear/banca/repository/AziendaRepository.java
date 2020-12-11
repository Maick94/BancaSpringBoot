package it.begear.banca.repository;




import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import it.begear.banca.entity.Azienda;




public interface AziendaRepository extends JpaRepository<Azienda, Integer> {
	
	@Query("SELECT a FROM Azienda a WHERE a.pIva LIKE %?1%")
	public Azienda searchPIva(String s);
	
	

	
}