package it.begear.banca.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import it.begear.banca.entity.Conto;

public interface ContoRepository extends JpaRepository<Conto, Integer>{


}
