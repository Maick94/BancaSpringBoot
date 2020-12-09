package it.begear.banca.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import it.begear.banca.entity.Azienda;

public interface AziendaRepository extends JpaRepository<Azienda, Integer> {

}
