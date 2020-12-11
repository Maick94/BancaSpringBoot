package it.begear.banca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.begear.banca.entity.Deposito;



public interface DepositoRepository extends JpaRepository<Deposito, Integer>{
	
	

}
