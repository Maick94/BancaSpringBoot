package it.begear.banca.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import it.begear.banca.entity.Persona;

public interface PersonaRepository extends JpaRepository<Persona, Integer> {
	


}
