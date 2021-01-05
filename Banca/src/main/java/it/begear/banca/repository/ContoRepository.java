package it.begear.banca.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.begear.banca.entity.Conto;

public interface ContoRepository extends JpaRepository<Conto, Integer>{
	@Query("SELECT AVG(quantita) FROM Deposito WHERE idConto =?1 ")
    Double getMediaQuantitaContoPersona(int id);
}
