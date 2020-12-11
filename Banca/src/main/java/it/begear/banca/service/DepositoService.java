package it.begear.banca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.begear.banca.entity.Deposito;
import it.begear.banca.repository.DepositoRepository;

@Service
public class DepositoService {
	
	@Autowired
	private DepositoRepository depositoRepository; 

	public List<Deposito> getAllList() {
		return depositoRepository.findAll();
	}
	
	public void saveDeposito(Deposito deposito) {
		depositoRepository.save(deposito);
	}
	
	public Deposito getDeposito(int id) {
		return depositoRepository.findById(id).get();
	}
	
	
}
