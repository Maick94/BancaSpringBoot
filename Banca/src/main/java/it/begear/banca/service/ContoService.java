package it.begear.banca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.begear.banca.entity.Conto;
import it.begear.banca.repository.ContoRepository;

@Service
public class ContoService {
	
	@Autowired
	private ContoRepository contoRepository; 

	
	public List<Conto> getAllList() {
		return contoRepository.findAll();
	}
	
	public void saveConto(Conto conto) {
		contoRepository.save(conto);
	}
	
	public Conto getConto(int id) {
		return contoRepository.findById(id).get();
	}


	public void deleteConto(int id) {
		contoRepository.deleteById(id);
	}
}
